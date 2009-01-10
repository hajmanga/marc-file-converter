package ppa.marc;

import java.util.ArrayList;
import java.util.List;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

import junit.framework.TestCase;

import static org.easymock.classextension.EasyMock.*;

public class JsapBasedArgumentParserTest extends TestCase {

	private static final String ERROR_TWO = "error 2";
	private static final String ERROR_ONE = "error 1";
	private static final String OUTPUT_FILE = "output file";
	private static final String TO = "UNIMARC_TEXT";
	private static final String FROM = "MARC21";
	private static final String FILENAME = "filename";
	private static final String[] ARGUMENTS = new String[0];
	private static final String USAGE = "usage";
	private static final String HELP = "help";
	
	String[] parsedParameters;
	
	JSAP jsap = createStrictMock(JSAP.class);
	JSAPResult jsapResult = createStrictMock(JSAPResult.class);
	List<String> errorMessages = new ArrayList<String>();
	
	ArgumentParser argumentParser;

	protected void setUp() throws Exception {
		errorMessages.add(ERROR_ONE);
		errorMessages.add(ERROR_TWO);
	}

	protected void tearDown() throws Exception {
		verify(jsap);
		verify(jsapResult);
	}
	
	public void testGrantedJsapReportsErrorThenThrowsParameterException() throws Exception {
		try {
			createParserAndParseArguments();
			expect(jsapResult.success()).andReturn(false);
			expect(jsapResult.getErrorMessageIterator()).andReturn(errorMessages.iterator());
			replay(jsapResult);
			argumentParser.parseArgument(ARGUMENTS);
			fail();
		} catch(ParameterException expected) {
			assertEquals("There were errors in command line arguments:\n- " + ERROR_ONE + "\n- " + ERROR_TWO + "\n", expected.getMessage());
		}
	}

	public void testGrantedJsapResultContainsManadatoryParametersThenThoseAreSetInConfiguration() throws Exception {
		createParserAndParseArguments();
		expectMandatoryParametersWithOutputFileAndValidate(null, false);
		Configuration expectedConfiguration = createExpectedConfigurationWithOutputFileAndValidate(null, false);
		assertEquals(expectedConfiguration, argumentParser.parseArgument(ARGUMENTS));
	}

	public void testGrantedOutputFileIsSpecificedThenItIsSetInConfiguration() throws Exception {
		createParserAndParseArguments();
		expectMandatoryParametersWithOutputFileAndValidate(OUTPUT_FILE, false);
		Configuration expectedConfiguration = createExpectedConfigurationWithOutputFileAndValidate(OUTPUT_FILE, false);
		assertEquals(expectedConfiguration, argumentParser.parseArgument(ARGUMENTS));
	}

	public void testGrantedValidateIsSpecifiedThenItIsSetInConfiguration() throws Exception {
		createParserAndParseArguments();
		expectMandatoryParametersWithOutputFileAndValidate(null, true);
		Configuration expectedConfiguration = createExpectedConfigurationWithOutputFileAndValidate(null, true);
		assertEquals(expectedConfiguration, argumentParser.parseArgument(ARGUMENTS));
	}

	public void testGrantedHelpIsRequestedThenReturnsCombinationOfUsageAndHelpFromJsap() throws Exception {
		createParserAndGetHelp();
		assertEquals("Usage:\n  MarcConverter " + USAGE + "\nwhere allowed parameters are:\n\n" + HELP, argumentParser.getHelp());
	}
	
	private Configuration createExpectedConfigurationWithOutputFileAndValidate(String outputFile, boolean validate) {
		Configuration expectedConfiguration = new Configuration(FILENAME, MarcFormat.MARC21, MarcFormat.UNIMARC_TEXT);
		expectedConfiguration.setOutputFilename(outputFile);
		expectedConfiguration.setValidate(validate);
		return expectedConfiguration;
	}
	
	private void expectMandatoryParametersWithOutputFileAndValidate(String outputFilename, boolean validate) {
		expect(jsapResult.success()).andReturn(true);
		expect(jsapResult.getString(JsapParameters.INPUT_FILE.getID())).andReturn(FILENAME);
		expect(jsapResult.getString(JsapParameters.FROM_FORMAT.getID())).andReturn(FROM);
		expect(jsapResult.getString(JsapParameters.TO_FORMAT.getID())).andReturn(TO);
		expect(jsapResult.getString(JsapParameters.OUTPUT_FILE.getID())).andReturn(outputFilename);
		expect(jsapResult.getBoolean(JsapParameters.VALIDATE.getID())).andReturn(validate);
		replay(jsapResult);
	}

	private void createParserAndParseArguments() throws JSAPException {
		expectRequiredParameters();
		expect(jsap.parse(ARGUMENTS)).andReturn(jsapResult);
		instantiateParser();
	}

	private void createParserAndGetHelp() throws JSAPException {
		expectRequiredParameters();
		expect(jsap.getUsage()).andReturn(USAGE);
		expect(jsap.getHelp()).andReturn(HELP);
		replay(jsapResult);
		instantiateParser();
	}

	private void instantiateParser() throws JSAPException {
		replay(jsap);
		argumentParser = new JsapBasedArgumentParser(jsap);
	}

	private void expectRequiredParameters() throws JSAPException {
		jsap.registerParameter(JsapParameters.VALIDATE);
		jsap.registerParameter(JsapParameters.TO_FORMAT);
		jsap.registerParameter(JsapParameters.OUTPUT_FILE);
		jsap.registerParameter(JsapParameters.FROM_FORMAT);
		jsap.registerParameter(JsapParameters.INPUT_FILE);
	}
	
}
