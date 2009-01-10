package ppa.marc.validator;

import ppa.marc.validator.Severity;
import junit.framework.TestCase;

public class SeverityTest extends TestCase {

	public void testSeverityLevels() throws Exception {
		assertNotSame(Severity.WARNING, Severity.ERROR);
	}
	
	public void testEquality() throws Exception {
		assertEquals(Severity.WARNING, Severity.WARNING);
		assertFalse(Severity.WARNING.equals(Severity.ERROR));
	}
	
	public void testToString() throws Exception {
		assertEquals("WARNING", Severity.WARNING.toString());
		assertEquals("ERROR", Severity.ERROR.toString());
	}
	
}
