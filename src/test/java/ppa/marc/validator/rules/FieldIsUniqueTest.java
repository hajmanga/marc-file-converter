package ppa.marc.validator.rules;

import java.util.List;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import ppa.marc.validator.Rule;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;
import ppa.marc.validator.rules.FieldIsUnique;
import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

public class FieldIsUniqueTest extends TestCase {

	Predicate fieldByIdPredicate = createStrictMock(Predicate.class);
	Rule fieldIsUnique = new FieldIsUnique(Severity.ERROR, fieldByIdPredicate);

	Record record = new Record("id");

	protected void tearDown() throws Exception {
		verify(fieldByIdPredicate);
	}
	
	public void testReturnsNothingIfNoFields() throws Exception {
		addAndExpectFieldTimes(0);
		List<ValidationMessage> messages = fieldIsUnique.validate(record);
		assertEquals(0, messages.size());
	}
	
	public void testReturnsNothingIfFieldsAreUnique() throws Exception {
		addAndExpectFieldTimes(1);
		List<ValidationMessage> messages = fieldIsUnique.validate(record);
		assertEquals(0, messages.size());
	}

	public void testReturnsOneValidationMessageForNonUniqueFields() throws Exception {
		addAndExpectFieldTimes(2);
		List<ValidationMessage> messages = fieldIsUnique.validate(record);
		assertEquals(1, messages.size());
		assertEquals(new ValidationMessage(Severity.ERROR, "There are 2 instances of field 010; expected one."), messages.get(0));
	}

	private void addAndExpectFieldTimes(int times) {
		for(int i = 0; i < times; ++i) {
			record.getFields().add(new Field(10));
			expect(fieldByIdPredicate.evaluate(record.getFields().get(0))).andReturn(true);
		}
		replay(fieldByIdPredicate);
	}

}
