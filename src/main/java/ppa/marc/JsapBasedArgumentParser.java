package ppa.marc;

import java.util.Iterator;

import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;

public class JsapBasedArgumentParser implements ArgumentParser {

	private final JSAP jsap;

	public JsapBasedArgumentParser(JSAP jsap) throws JSAPException {
		this.jsap = jsap;
		jsap.registerParameter(JsapParameters.VALIDATE);
		jsap.registerParameter(JsapParameters.TO_FORMAT);
		jsap.registerParameter(JsapParameters.OUTPUT_FILE);
		jsap.registerParameter(JsapParameters.FROM_FORMAT);
		jsap.registerParameter(JsapParameters.INPUT_FILE);
	}

	public Configuration parseArgument(String[] arguments) throws ParameterException {
		JSAPResult jsapResult = jsap.parse(arguments);
		if(jsapResult.success()) {
			return extractConfiguration(jsapResult);
		}
		else {
			throw new ParameterException(extractErrorMessage(jsapResult), true);
		}
	}

	private Configuration extractConfiguration(JSAPResult jsapResult) throws ParameterException {
		Configuration configuration = new Configuration(jsapResult.getString(JsapParameters.INPUT_FILE.getID()), inputFormatToString(jsapResult, JsapParameters.FROM_FORMAT.getID()), outputFormatToString(jsapResult, JsapParameters.TO_FORMAT.getID()));
		configuration.setOutputFilename(jsapResult.getString(JsapParameters.OUTPUT_FILE.getID()));
		configuration.setValidate(jsapResult.getBoolean(JsapParameters.VALIDATE.getID()));
		return configuration;
	}

	private String extractErrorMessage(JSAPResult jsapResult) {
		@SuppressWarnings("rawtypes")
		Iterator it = jsapResult.getErrorMessageIterator();
		StringBuilder stringBuilder = new StringBuilder("There were errors in command line arguments:\n");
		while(it.hasNext()) {
			stringBuilder.append("- " + it.next() + "\n");
		}
		return stringBuilder.toString();
	}

	private MarcFormat inputFormatToString(JSAPResult jsapResult, String id) throws ParameterException {
		return MarcFormat.inputFormatFromString(jsapResult.getString(id));
	}

	private MarcFormat outputFormatToString(JSAPResult jsapResult, String id) throws ParameterException {
		return MarcFormat.outputFormatFromString(jsapResult.getString(id));
	}
	
	public String getHelp() {
		return "Usage:\n  MarcConverter " + jsap.getUsage() + "\nwhere allowed parameters are:\n\n" + jsap.getHelp();
	}

}
