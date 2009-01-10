package ppa.marc;

import junit.framework.TestCase;

public class TypeBasedFormatBeanLoaderTest extends TestCase {

	private static final MarcFormat TO_FORMAT = MarcFormat.MARC21;
	private static final MarcFormat FROM_FORMAT = MarcFormat.UNIMARC_TEXT;
	private static final MarcFormat NON_EXISTING_FORMAT = MarcFormat.MARC21;
	private static final MarcFormat TEST_FORMAT = MarcFormat.UNIMARC_TEXT;
	
	FormatBeanLoader loader = new TypeBasedFormatBeanLoader("Test");

	public void testGrantedInputFileDoesNotExistThenThrowsRuntimeException() throws Exception {
		try {
			loader.loadBean(NON_EXISTING_FORMAT);
			fail();
		} catch(RuntimeException expected) {
		}
	}

	public void testGrantedInputFileExistsThenReturnsTheBean() throws Exception {
		String bean = (String) loader.loadBean(TEST_FORMAT);
		assertEquals("abc", bean);
	}

	public void testGrantedInputFileDoesNotExistThenTwoFormatLoaderThrowsRuntimeException() throws Exception {
		try {
			loader.loadBean(NON_EXISTING_FORMAT, NON_EXISTING_FORMAT);
			fail();
		} catch(RuntimeException expected) {
		}
	}

	public void testGrantedInputFileExistsThenTwoFormatLoaderReturnsTheBean() throws Exception {
		String bean = (String) loader.loadBean(FROM_FORMAT, TO_FORMAT);
		assertEquals("cba", bean);
	}
	
}
