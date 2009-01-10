package ppa.marc.validator;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class NotSupportedValidatorTest extends TestCase {

	Validator validator = new NotSupportedValidator();
	List<ValidationMessage> expectedValidationMessages = new ArrayList<ValidationMessage>();
	
	public void testGrantedValidateIsCalledThenReturnsOneErrorTellingThatValidationIsNotSupportedForTheFormat() throws Exception {
		expectedValidationMessages.add(new ValidationMessage(Severity.ERROR, "Validation isn't supported for selected output format."));
		assertEquals(expectedValidationMessages, validator.validate(null));
	}
	
}
