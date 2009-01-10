package ppa.marc.validator.rules;

import java.util.ArrayList;
import java.util.List;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import ppa.marc.validator.Rule;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;
import ppa.marc.validator.rules.RecordHasSubFields;
import ppa.marc.validator.rules.util.SubFieldAnalyser;
import junit.framework.TestCase;

import org.apache.commons.collections.Predicate;
import static org.easymock.EasyMock.*;

public class RecordHasSubFieldTest extends TestCase {

	Predicate fieldByIdSelector = createStrictMock(Predicate.class);
	SubFieldAnalyser missingSubFieldIdDetector = createStrictMock(SubFieldAnalyser.class);

	List<Character> expectedMissingSubFields = new ArrayList<Character>();
	
	Record record = new Record("id");
	Rule rule = new RecordHasSubFields(Severity.WARNING, fieldByIdSelector, missingSubFieldIdDetector);

	protected void setUp() throws Exception {
		record.getFields().add(new Field(10));
		expect(fieldByIdSelector.evaluate(record.getFields().get(0))).andReturn(true);
		replay(fieldByIdSelector);
		expect(missingSubFieldIdDetector.analyse(record.getFields().get(0))).andReturn(expectedMissingSubFields);
		replay(missingSubFieldIdDetector);
	}
	
	protected void tearDown() throws Exception {
		verify(fieldByIdSelector);
		verify(missingSubFieldIdDetector);
	}
	
	public void testReturnsNothingIfSubFieldsExist() throws Exception {
		assertEquals(0, rule.validate(record).size());
	}
	
	public void testReturnsWarningForMissingSubField() throws Exception {
		expectedMissingSubFields.add('b');
		List<ValidationMessage> messages = rule.validate(record); 
		assertEquals(1, messages.size());
		assertEquals(new ValidationMessage(Severity.WARNING, "Field 010 should have subfield b."), messages.get(0));
	}

	public void testReturnsWarningForMissingSubFields() throws Exception {
		expectedMissingSubFields.add('a');
		expectedMissingSubFields.add('b');
		expectedMissingSubFields.add('c');
		List<ValidationMessage> messages = rule.validate(record); 
		assertEquals(1, messages.size());
		assertEquals(new ValidationMessage(Severity.WARNING, "Field 010 should have subfields a, b and c."), messages.get(0));
	}
	
}
