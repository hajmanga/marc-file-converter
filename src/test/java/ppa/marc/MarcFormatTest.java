package ppa.marc;

import junit.framework.TestCase;

public class MarcFormatTest extends TestCase {

	public void testAssertMarcTwentyOneAndUnimarcTextAreNotEqual() throws Exception {
		assertFalse(MarcFormat.MARC21.equals(MarcFormat.UNIMARC_TEXT));
	}
	
	public void testGrantedUnimarcTextIsGivenToParserThenReturnsUnimarcText() throws Exception {
		assertEquals(MarcFormat.UNIMARC_TEXT, MarcFormat.fromString("UNIMARC_TEXT"));
	}

	public void testGrantedMarc21IsGivenToParserThenReturnsMarc21() throws Exception {
		assertEquals(MarcFormat.MARC21, MarcFormat.fromString("MARC21"));
	}

	public void testGrantedMarc21TextIsGivenToParserThenReturnsMarc21Text() throws Exception {
		assertEquals(MarcFormat.MARC21_TEXT, MarcFormat.fromString("MARC21_TEXT"));
	}

	public void testGrantedUnimarcTextIsGivenInMixedCaseToParserThenReturnsUnimarcText() throws Exception {
		assertEquals(MarcFormat.UNIMARC_TEXT, MarcFormat.fromString("Unimarc_text"));
	}

	public void testGrantedFormatIsUnknownThenThrowsRuntimeException() throws Exception {
		try {
			MarcFormat.fromString("unknown");
			fail();
		} catch(IllegalArgumentException expected) {
		}
	}

	public void testGrantedFormatIsMarc21ThenToStringShowsItAsMarc21() throws Exception {
		assertEquals("MARC21", MarcFormat.MARC21.toString());
	}

	public void testGrantedFormatIsUnimarcTextThenToStringShowsItAsUnimarcText() throws Exception {
		assertEquals("UNIMARC_TEXT", MarcFormat.UNIMARC_TEXT.toString());
	}
	
}
