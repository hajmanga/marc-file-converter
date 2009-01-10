package ppa.marc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import ppa.marc.converter.ConversionController;
import ppa.marc.domain.Record;
import ppa.marc.validator.ValidationMessage;
import ppa.marc.validator.Validator;

public class MarcConverter {

	private OutputStream errorMessageStream;
	private final RecordReader recordReader;
	private final Configuration configuration;
	private final ConversionController conversionController;
	private final RecordWriter recordWriter;
	private final StreamOpener streamOpener;
	private final Validator validator;

	private boolean isFirstRecord = true;
	
	public MarcConverter(Configuration configuration, RecordReader recordReader, ConversionController conversionController, RecordWriter recordWriter, StreamOpener streamOpener, Validator validator) {
		this.configuration = configuration;
		this.recordReader = recordReader;
		this.conversionController = conversionController;
		this.recordWriter = recordWriter;
		this.streamOpener = streamOpener;
		this.validator = validator;
	}

	public void execute() throws IOException {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			errorMessageStream = streamOpener.getErrorStream();
			inputStream = streamOpener.openInputStream(configuration.getInputFile());
			List<Record> records = convertRecords(inputStream);
			outputStream = streamOpener.openOutputStream(configuration.getOutputFile());
			recordWriter.writeRecords(outputStream, records);
		} catch (IOException e) {
			errorMessageStream.write((e.getMessage() + "\n").getBytes());
		} finally {
			if(inputStream != null) streamOpener.close(inputStream);
			if(outputStream != null) streamOpener.close(outputStream);
		}
	}

	private List<Record> convertRecords(InputStream inputStream) throws IOException {
		List<Record> records = recordReader.read(inputStream); 
		for(Record record : records) {
			conversionController.convert(record);
			validateRecord(record);
		}
		return records;
	}

	private void validateRecord(Record record) throws IOException {
		if(configuration.isValidate()) {
			List<ValidationMessage> validationMessages = validator.validate(record);
			if(!validationMessages.isEmpty()) {
				writeWarnings(record, validationMessages);
			}
		}
	}

	private void writeWarnings(Record record, List<ValidationMessage> validationMessages) throws IOException {
		if(!isFirstRecord) errorMessageStream.write('\n');
		errorMessageStream.write(("Record " + record.getName() + " has following problems:\n").getBytes());
		for(ValidationMessage validationMessage : validationMessages) {
			errorMessageStream.write(("  " + validationMessage + "\n").getBytes());
		}
		isFirstRecord = false;
	}

	void setErrorMessageStreamForUnitTesting(ByteArrayOutputStream outputStream) {
		this.errorMessageStream = outputStream;
	}

}
