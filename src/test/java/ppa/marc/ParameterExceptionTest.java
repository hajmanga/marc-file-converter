package ppa.marc;

import junit.framework.TestCase;

public class ParameterExceptionTest extends TestCase {

	private static final String MESSAGE = "message";
	
	Exception exception = new ParameterException(MESSAGE);
	
	public void testGrantedMessageIsGivenThenItsSetAsCause() throws Exception {
		assertEquals(MESSAGE, exception.getMessage());
	}
	
	public void testGrantedConstructedWithOnlyCauseThenShowHelpIsFalse() throws Exception {
		assertFalse(new ParameterException("").isShowHelp());
	}

	public void testGrantedConstructedWithShowHelpTrueThenShowHelpIsTrue() throws Exception {
		assertTrue(new ParameterException("", true).isShowHelp());
	}
	
}
