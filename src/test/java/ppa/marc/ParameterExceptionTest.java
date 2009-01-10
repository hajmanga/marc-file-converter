package ppa.marc;

import junit.framework.TestCase;

public class ParameterExceptionTest extends TestCase {

	private static final String MESSAGE = "message";
	
	Exception exception = new ParameterException(MESSAGE);
	
	public void testGrantedMessageIsGivenThenItsSetAsCause() throws Exception {
		assertEquals(MESSAGE, exception.getMessage());
	}
	
}
