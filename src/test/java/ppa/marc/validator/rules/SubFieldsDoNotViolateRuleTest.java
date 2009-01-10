package ppa.marc.validator.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import ppa.marc.validator.Rule;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;
import ppa.marc.validator.rules.SubFieldsDoNotViolateRule;
import ppa.marc.validator.rules.util.SubFieldAnalyser;
import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

public class SubFieldsDoNotViolateRuleTest extends TestCase {

	String message = "Validation message";
	Severity severity = Severity.ERROR;
	Predicate fieldByIdPredicate = createStrictMock(Predicate.class);
	SubFieldAnalyser subFieldsThatStartWith = createStrictMock(SubFieldAnalyser.class);
	
	List<Character> matchingSubFieldIds = new ArrayList<Character>();
	
	Record record = new Record("id");
	Field field = new Field(10);
	
	Rule rule = new SubFieldsDoNotViolateRule(message, severity, fieldByIdPredicate, subFieldsThatStartWith);
	
	protected void setUp() throws Exception {
		record.getFields().add(field);
	}
	
	protected void tearDown() throws Exception {
		verify(fieldByIdPredicate);
		verify(subFieldsThatStartWith);
	}
	
	public void testReturnsNothingIfNoMatchingFields() throws Exception {
		expect(fieldByIdPredicate.evaluate(field)).andReturn(false);
		replayMocks();
		assertEquals(0, rule.validate(record).size());
	}

	public void testReturnsNothingIfSubFieldsDoNotStartWithThePrefix() throws Exception {
		expectAndReturnMatchingSubFieldId();
		assertEquals(0, rule.validate(record).size());
	}

	public void testReturnsValidationMessageForMatchingSubFieldId() throws Exception {
		matchingSubFieldIds.add('a');
		expectAndReturnMatchingSubFieldId();
		ValidationMessage message = assertMessageCountAndSeverity();
		assertEquals(this.message + " field 010's subfield a.", message.getMessage());
	}

	public void testReturnsValidationMessageForMatchingSubFieldIds() throws Exception {
		matchingSubFieldIds.add('a');
		matchingSubFieldIds.add('b');
		matchingSubFieldIds.add('c');
		expectAndReturnMatchingSubFieldId();
		ValidationMessage message = assertMessageCountAndSeverity();
		assertEquals(this.message + " field 010's subfields a, b and c.", message.getMessage());
	}

	private ValidationMessage assertMessageCountAndSeverity() {
		List<ValidationMessage> messages = rule.validate(record);
		ValidationMessage message = messages.get(0);
		assertEquals(1, messages.size());
		assertEquals(severity, message.getSeverity());
		return message;
	}
	
	private void expectAndReturnMatchingSubFieldId() {
		expect(fieldByIdPredicate.evaluate(field)).andReturn(true);
		expect(subFieldsThatStartWith.analyse(field)).andReturn(matchingSubFieldIds);
		replayMocks();
	}
	
	private void replayMocks() {
		replay(fieldByIdPredicate);
		replay(subFieldsThatStartWith);
	}
	
}
