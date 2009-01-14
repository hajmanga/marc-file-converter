package ppa.marc;

import junit.framework.TestCase;

public class MarcFormatTest extends TestCase {

	public void testAssertMarcTwentyOneAndUnimarcTextAreNotEqual() throws Exception {
		assertFalse(MarcFormat.MARC21.equals(MarcFormat.UNIMARC_TEXT));
	}
	
	public void testGrantedUnimarcTextIsGivenToParserThenReturnsUnimarcText() throws Exception {
		assertEquals(MarcFormat.UNIMARC_TEXT, MarcFormat.outputFormatFromString("UNIMARC_TEXT"));
	}

	public void testGrantedMarc21IsGivenToParserThenReturnsMarc21() throws Exception {
		assertEquals(MarcFormat.MARC21, MarcFormat.outputFormatFromString("MARC21"));
	}

	public void testGrantedMarc21TextIsGivenToParserThenReturnsMarc21Text() throws Exception {
		assertEquals(MarcFormat.MARC21_TEXT, MarcFormat.outputFormatFromString("MARC21_TEXT"));
	}

	public void testGrantedFormatIsMarc21ThenToStringShowsItAsMarc21() throws Exception {
		assertEquals("MARC21", MarcFormat.MARC21.toString());
	}

	public void testGrantedFormatIsUnimarcTextThenToStringShowsItAsUnimarcText() throws Exception {
		assertEquals("UNIMARC_TEXT", MarcFormat.UNIMARC_TEXT.toString());
	}
	
	public void testGrantedInputFormatIsUnsupportedThenExceptionContainsDescriptiveCause() throws Exception {
		try {
			MarcFormat.inputFormatFromString("unimarc_text");
			fail();
		} catch(ParameterException expected) {
			assertEquals("Input format 'unimarc_text' isn't supported; supported input format is MARC21.", expected.getMessage());
		}
	}

	public void testGrantedOutputFormatIsUnsupportedThenExceptionContainsDescriptiveCause() throws Exception {
		try {
			MarcFormat.outputFormatFromString("unknown");
			fail();
		} catch(ParameterException expected) {
			assertEquals("Output format 'unknown' isn't supported; supported output formats are MARC21, MARC21_TEXT and UNIMARC_TEXT.", expected.getMessage());
		}
	}
	
	public void testGrantedMarc21IsGivenToInputFormatParserThenReturnsMarc21() throws Exception {
		assertEquals(MarcFormat.MARC21, MarcFormat.inputFormatFromString("MARC21"));
	}
	
}
