package ppa.marc;

import java.io.File;

import org.apache.commons.lang.builder.EqualsBuilder;

public class Configuration {

	private final File inputFile;
	private File outputFile;
	private final MarcFormat inputFormat;
	private MarcFormat outputFormat;
	private boolean validate;
	
	public Configuration(String inputFilename, MarcFormat inputFormat, MarcFormat outputFormat) {
		this.inputFormat = inputFormat;
		this.outputFormat = outputFormat;
		this.inputFile = new File(inputFilename);
	}

	public boolean isValidate() {
		return validate;
	}

	public File getInputFile() {
		return inputFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFilename(String outputFilename) {
		if(outputFilename == null) {
			this.outputFile = null;
		}
		else {
			this.outputFile = new File(outputFilename);
		}
	}

	public MarcFormat getInputFormat() {
		return inputFormat;
	}

	public MarcFormat getOutputFormat() {
		return outputFormat;
	}

	public boolean equals(Object object) {
		if(object instanceof Configuration) {
			Configuration compareTo = (Configuration) object;
			return new EqualsBuilder().append(inputFile, compareTo.inputFile).append(outputFile, compareTo.outputFile).append(inputFormat, compareTo.inputFormat).append(outputFormat, compareTo.outputFormat).append(validate, compareTo.validate).isEquals();
		}
		return false;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
}
