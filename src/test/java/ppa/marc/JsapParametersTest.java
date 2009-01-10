package ppa.marc;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;

import junit.framework.TestCase;

public class JsapParametersTest extends TestCase {

	public void testAssertValidateIsSwitch() throws Exception {
		assertTrue(JsapParameters.VALIDATE instanceof Switch);
	}
	
	public void testAssertValidateParameterHelp() throws Exception {
		assertEquals("Validate conversion result and print out validation messages.", JsapParameters.VALIDATE.getHelp());
	}

	public void testAssertToIsFlaggedOption() throws Exception {
		assertTrue(JsapParameters.TO_FORMAT instanceof FlaggedOption);
	}
	
	public void testAssertToParameterHelp() throws Exception {
		assertEquals("Defines target format.", JsapParameters.TO_FORMAT.getHelp());
	}
	
	public void testAssertToDefaultsToUnimarcText() throws Exception {
		assertEquals("UNIMARC_TEXT", JsapParameters.TO_FORMAT.getDefault()[0]);
	}

	public void testAssertOutoutIsFlaggedOption() throws Exception {
		assertTrue(JsapParameters.OUTPUT_FILE instanceof FlaggedOption);
	}

	public void testAssertOutputParameterHelp() throws Exception {
		assertEquals("Output file's name.  If undefined, then output is shown on display.", JsapParameters.OUTPUT_FILE.getHelp());
	}

	public void testAssertFormatIsFlaggedOption() throws Exception {
		assertTrue(JsapParameters.FROM_FORMAT instanceof FlaggedOption);
	}

	public void testAssertFormatParameterHelp() throws Exception {
		assertEquals("Input file's format.", JsapParameters.FROM_FORMAT.getHelp());
	}

	public void testAssertFormatDefaultsToMarc21() throws Exception {
		assertEquals("MARC21", JsapParameters.FROM_FORMAT.getDefault()[0]);
	}
	
	public void testAssertInputFileIsUnflaggedOption() throws Exception {
		assertTrue(JsapParameters.INPUT_FILE instanceof UnflaggedOption);
	}

	public void testAssertInputParameterHelp() throws Exception {
		assertEquals("Input file's name.", JsapParameters.INPUT_FILE.getHelp());
	}

	public void testAssertInputFilenameIsRequired() throws Exception {
		assertTrue(JsapParameters.INPUT_FILE.required());
	}

	public void testAssertValidateHasFlags() throws Exception {
		assertEquals('v', JsapParameters.VALIDATE.getShortFlag());
		assertEquals("validate", JsapParameters.VALIDATE.getLongFlag());
	}

	public void testAssertToHasFlags() throws Exception {
		assertEquals('t', JsapParameters.TO_FORMAT.getShortFlag());
		assertEquals("to", JsapParameters.TO_FORMAT.getLongFlag());
	}

	public void testAssertOutputHasFlags() throws Exception {
		assertEquals('o', JsapParameters.OUTPUT_FILE.getShortFlag());
		assertEquals("output", JsapParameters.OUTPUT_FILE.getLongFlag());
	}

	public void testAssertFromFormatHasFlags() throws Exception {
		assertEquals('f', JsapParameters.FROM_FORMAT.getShortFlag());
		assertEquals("from", JsapParameters.FROM_FORMAT.getLongFlag());
	}
	
}
