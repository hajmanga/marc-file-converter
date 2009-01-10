package ppa.marc.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ppa.marc.domain.Record;
import ppa.marc.validator.Rule;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;
import ppa.marc.validator.Validator;

import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

public class ValidatorTest extends TestCase {

	List<Rule> validators = new ArrayList<Rule>();
	Validator validator = new Validator(validators);
	Rule[] rule = new Rule[] {
			createStrictMock(Rule.class),
			createStrictMock(Rule.class)
	};
	Record record = new Record("id");
	List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
	List<ValidationMessage> expectedMessages = new ArrayList<ValidationMessage>();
	
	protected void setUp() throws Exception {
		validationMessages.add(new ValidationMessage(Severity.ERROR, ""));
		validators.addAll(Arrays.asList(rule));
		for(int i = 0; i < rule.length; ++i) {
			expect(rule[i].validate(record)).andReturn(validationMessages);
			expectedMessages.addAll(validationMessages);
			replay(rule[i]);
		}
	}
	
	protected void tearDown() throws Exception {
		for(int i = 0; i < rule.length; ++i) {
			verify(rule[i]);
		}
	}
	
	public void testCallsValidatorsAndReturnsLists() throws Exception {
		assertEquals(expectedMessages, validator.validate(record));
	}
	
}
