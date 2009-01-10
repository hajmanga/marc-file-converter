package ppa.marc.converter.generic;

import org.apache.commons.collections.Predicate;

import ppa.marc.converter.generic.FieldCopier;
import ppa.marc.converter.generic.RecordConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import ppa.marc.domain.SubField;
import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

public class FieldCopierTest extends TestCase {

	Predicate subFieldByIdPredicate = createStrictMock(Predicate.class);
	Predicate fieldByIdPredicate = createStrictMock(Predicate.class);
	RecordConverter converter = new FieldCopier(fieldByIdPredicate, subFieldByIdPredicate, 20, '1', '2');
	Record record = new Record("id");
	
	protected void setUp() throws Exception {
		addFieldWithOptionalSubFields(5, new char[0]);
	}
	
	public void testDoesNotCopyIfFieldIsNotInSource() throws Exception {
		converter.convert(record);
		assertEquals(1, record.getFields().size());
		assertEquals(5, record.getFields().get(0).getId());
	}

	public void testCopiesTheSourceFieldToNewField() throws Exception {
		addFieldWithOptionalSubFields(10, new char[0]);
		expectAndReplayMockFieldPredicate();
		converter.convert(record);
		assertFieldCopying();
	}

	public void testDoesNotCopyUnwantedSubfields() throws Exception {
		addFieldWithOptionalSubFields(10, new char[] {'x'});
		expectAndReplayMockFieldPredicate();
		converter.convert(record);
		assertEquals(0, getCopiedField().getSubFields().size());
	}

	public void testCopiesWantedSubField() throws Exception {
		addFieldWithOptionalSubFields(10, new char[] {'a'});
		expectAndReplayMockFieldPredicate();
		expectAndReplaySubFieldTransformation();
		converter.convert(record);
		verify(subFieldByIdPredicate);
		verify(fieldByIdPredicate);
		assertEquals('a', getCopiedSubField().getId().charValue());
		assertNotSame(getOriginalSubField(), getCopiedSubField());
	}

	private Field getCopiedField() {
		return record.getFields().get(2);
	}
	
	private SubField getOriginalSubField() {
		return record.getFields().get(1).getSubFields().get(0);
	}

	private SubField getCopiedSubField() {
		return getCopiedField().getSubFields().get(0);
	}
	
	private void expectAndReplaySubFieldTransformation() {
		expect(subFieldByIdPredicate.evaluate(getOriginalSubField())).andReturn(true);
		replay(subFieldByIdPredicate);
	}
	
	private void addFieldWithOptionalSubFields(int id, char[] subFieldIds) {
		Field field = new Field(id);
		for(int i = 0; i < subFieldIds.length; ++i) {
			field.getSubFields().add(new SubField(subFieldIds[i], "data"));
		}
		record.getFields().add(field);
	}
	
	private void assertFieldCopying() {
		assertEquals(3, record.getFields().size());
		assertEquals(10, record.getFields().get(1).getId());
		assertEquals(20, getCopiedField().getId());
		assertEquals('1', getCopiedField().getFirstIndicator());
		assertEquals('2', getCopiedField().getSecondIndicator());
		assertNotSame(record.getFields().get(1), getCopiedField());
		verify(fieldByIdPredicate);
	}

	private void expectAndReplayMockFieldPredicate() {
		expect(fieldByIdPredicate.evaluate(record.getFields().get(0))).andReturn(false);
		expect(fieldByIdPredicate.evaluate(record.getFields().get(1))).andReturn(true);
		replay(fieldByIdPredicate);
	}
	
}
