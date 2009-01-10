package ppa.marc;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;

public class JsapParameters {

	public static final Switch VALIDATE = new Switch("validate");
	public static final FlaggedOption TO_FORMAT = new FlaggedOption("to");
	public static final FlaggedOption OUTPUT_FILE = new FlaggedOption("output");
	public static final FlaggedOption FROM_FORMAT = new FlaggedOption("format");
	public static final UnflaggedOption INPUT_FILE = new UnflaggedOption("input");
	
	static {
		VALIDATE.setHelp("Validate conversion result and print out validation messages.");
		VALIDATE.setShortFlag('v');
		VALIDATE.setLongFlag("validate");

		TO_FORMAT.setHelp("Defines target format.");
		TO_FORMAT.addDefault("UNIMARC_TEXT");
		TO_FORMAT.setShortFlag('t');
		TO_FORMAT.setLongFlag("to");
		
		OUTPUT_FILE.setHelp("Output file's name.  If undefined, then output is shown on display.");
		OUTPUT_FILE.setShortFlag('o');
		OUTPUT_FILE.setLongFlag("output");
		
		FROM_FORMAT.setHelp("Input file's format.");
		FROM_FORMAT.addDefault("MARC21");
		FROM_FORMAT.setShortFlag('f');
		FROM_FORMAT.setLongFlag("from");
		
		INPUT_FILE.setHelp("Input file's name.");
		INPUT_FILE.setRequired(true);
	}
	
}
