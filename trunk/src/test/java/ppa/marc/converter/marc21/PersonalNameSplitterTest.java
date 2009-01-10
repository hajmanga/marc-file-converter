package ppa.marc.converter.marc21;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Predicate;

import ppa.marc.converter.FieldConverter;
import ppa.marc.converter.marc21.PersonalNameSplitter;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;
import junit.framework.TestCase;

public class PersonalNameSplitterTest extends TestCase {

	Predicate subFieldByIdSelector = createStrictMock(Predicate.class);
	Field field = new Field(100, '1', '_');
	FieldConverter converter = new PersonalNameSplitter(subFieldByIdSelector, true);
	List<SubField> expectedSubFields = new ArrayList<SubField>();

	protected void setUp() throws Exception {
		expectedSubFields.add(new SubField('a', "Ahopelto"));
		expectedSubFields.add(new SubField('b', "Pasi"));
		field.getSubFields().add(new SubField('a', "Ahopelto, Pasi"));
		field.getSubFields().add(new SubField('d', "1969-"));
		expectEvaluateOfSubFields();
	}

	protected void tearDown() throws Exception {
		assertEquals(expectedSubFields, field.getSubFields());
		verify(subFieldByIdSelector);
	}
	
	public void testSplitsAtFirstComma() throws Exception {
		replay(subFieldByIdSelector);
		converter.convert(field);
	}
	
	public void testDoesNotDeleteOtherFieldsIfNotRequested() throws Exception {
		expectedSubFields.add(new SubField('d', "1969-"));
		expectEvaluateOfSubFields();
		replay(subFieldByIdSelector);
		new PersonalNameSplitter(subFieldByIdSelector, false).convert(field);
	}

	private void expectEvaluateOfSubFields() {
		expect(subFieldByIdSelector.evaluate(field.getSubFields().get(0))).andReturn(true);
		expect(subFieldByIdSelector.evaluate(field.getSubFields().get(1))).andReturn(false);
	}
	
}
