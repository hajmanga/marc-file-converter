package ppa.marc.validator;

import junit.framework.TestCase;

public class ValidationMessageTest extends TestCase {

	private static final String TEXT = "text";

	public void testEquality() throws Exception {
		assertTrue(new ValidationMessage(Severity.ERROR, TEXT).equals(new ValidationMessage(Severity.ERROR, TEXT)));
	}

	public void testNonEqualityInCaseOfSeverity() throws Exception {
		assertFalse(new ValidationMessage(Severity.ERROR, TEXT).equals(new ValidationMessage(Severity.WARNING, TEXT)));
	}

	public void testNonEqualityInCaseOfText() throws Exception {
		assertFalse(new ValidationMessage(Severity.ERROR, TEXT).equals(new ValidationMessage(Severity.ERROR, TEXT + " ")));
	}
	
	public void testCanBeInstantiatedWithSeverityAndMessage() throws Exception {
		ValidationMessage validationMessage = new ValidationMessage(Severity.WARNING, "message");
		assertEquals(Severity.WARNING, validationMessage.getSeverity());
		assertEquals("message", validationMessage.getMessage());
	}
	
	public void testToString() throws Exception {
		assertEquals(Severity.ERROR + ": message", new ValidationMessage(Severity.ERROR, "message").toString());
	}
	
}
