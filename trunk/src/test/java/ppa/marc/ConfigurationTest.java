package ppa.marc;

import java.io.File;
import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {

	private static final String INPUT_FILE = "Input file";
	private static final String OUTPUT_FILE = "Output file";
	
	Configuration configuration = new Configuration(INPUT_FILE, MarcFormat.MARC21, MarcFormat.UNIMARC_TEXT);

	public void testGrantedValidateIsNotSetThenValidateIsFalse() throws Exception {
		assertFalse(configuration.isValidate());
	}
	
	public void testGrantedInputFileIsSetInConstructorThenItIsReturned() throws Exception {
		assertEquals(new File(INPUT_FILE), configuration.getInputFile());
	}

	public void testGrantedOutputFilenameIsSetThenItIsReturned() throws Exception {
		configuration.setOutputFilename(OUTPUT_FILE);
		assertEquals(new File(OUTPUT_FILE), configuration.getOutputFile());
	}
	
	public void testGrantedOutputFileIsUnsetThenReturnsNull() throws Exception {
		assertNull(configuration.getOutputFile());
	}

	public void testGrantedInputFormatIsMarc21InConstructorThenItIsAlsoReturned() throws Exception {
		assertEquals(MarcFormat.MARC21, configuration.getInputFormat());
	}
	
	public void testGrantedOutputFormatIsUnimarcTexthenItIsAlsoReturned() throws Exception {
		assertEquals(MarcFormat.UNIMARC_TEXT, configuration.getOutputFormat());
	}

	public void testGrantedParametersAreSameThenEqualsReturnsTrue() throws Exception {
		Configuration compareTo = new Configuration(configuration.getInputFile().getName(), configuration.getInputFormat(), configuration.getOutputFormat());
		assertEquals(compareTo, configuration);
	}
	
	public void testGrantedOutputFileIsSetToNullThenGetOutputFileReturnsNull() throws Exception {
		configuration.setOutputFilename(null);
		assertNull(configuration.getOutputFile());
	}
	
	public void testGrantedValidateIsSetToTrueThenItIsAlsoReturned() throws Exception {
		configuration.setValidate(true);
		assertTrue(configuration.isValidate());
	}
	
	public void testGrantedValidateDiffersInTwoInstancesThenEqualsReturnsFalse() throws Exception {
		Configuration compareTo = new Configuration(configuration.getInputFile().getName(), configuration.getInputFormat(), configuration.getOutputFormat());
		compareTo.setValidate(true);
		assertFalse(configuration.equals(compareTo));
	}
	
}
