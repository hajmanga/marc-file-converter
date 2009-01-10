package ppa.marc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ppa.marc.converter.ConversionController;
import ppa.marc.domain.Record;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;
import ppa.marc.validator.Validator;

import junit.framework.TestCase;
import static org.easymock.classextension.EasyMock.*;

public class MarcConverterTest extends TestCase {
	
	private static final String[] NAME = { "NAME 1", "NAME 2" };
	private static final Configuration CONFIGURATION = new Configuration("", null, null);
	private static final String MESSAGE = "message";
	
	List<ValidationMessage> validationMessagesForFirstRecord = new ArrayList<ValidationMessage>();
	List<ValidationMessage> validationMessagesForSecondRecord = new ArrayList<ValidationMessage>();
	List<Record> twoRecords = new ArrayList<Record>();
	String expectedValidationMessages;
	
	ConversionController conversionController = createStrictMock(ConversionController.class);
	RecordReader recordReader = createStrictMock(RecordReader.class);
	RecordWriter recordWriter = createStrictMock(RecordWriter.class);
	StreamOpener streamOpener = createStrictMock(StreamOpener.class);
	Validator validator = createStrictMock(Validator.class);
	
	ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[0]);
	ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
	MarcConverter converter = new MarcConverter(CONFIGURATION, recordReader, conversionController, recordWriter, streamOpener, validator);
	
	protected void setUp() throws Exception {
		converter.setErrorMessageStreamForUnitTesting(errorStream);
		twoRecords.add(createRecordWithName(NAME[0]));
		twoRecords.add(createRecordWithName(NAME[1]));
		validationMessagesForFirstRecord.add(new ValidationMessage(Severity.WARNING, "A"));
		validationMessagesForFirstRecord.add(new ValidationMessage(Severity.WARNING, "B"));
		validationMessagesForSecondRecord.add(new ValidationMessage(Severity.WARNING, "C"));
		expectedValidationMessages = "Record " + NAME[0] + " has following problems:\n  " + validationMessagesForFirstRecord.get(0) + "\n  " + validationMessagesForFirstRecord.get(1) + "\n\nRecord " + NAME[1] + " has following problems:\n  " + validationMessagesForSecondRecord.get(0) + "\n";
		expect(streamOpener.getErrorStream()).andReturn(errorStream);
	}
	
	protected void tearDown() throws Exception {
		verify(streamOpener);
		verify(recordReader);
		verify(conversionController);
		verify(recordWriter);
		verify(validator);
	}
	
	public void testGrantedReaderThrowsParameterExceptionThenErrorIsPrintedToOutputStream() throws Exception {
		expect(streamOpener.openInputStream(CONFIGURATION.getInputFile())).andReturn(inputStream);
		expect(recordReader.read(inputStream)).andThrow(new IOException(MESSAGE));
		streamOpener.close(inputStream);
		replayAndExpectErrorMessage(MESSAGE + "\n");
	}

	public void testGrantedWriterThrowsExceptionThenTheErrorIsPrintedToOutputStream() throws Exception {
		expectOpenInputAndOutputStreams();
		expectRecordConversionForTwoRecords();
		recordWriter.writeRecords(outputStream, twoRecords);
		expectLastCall().andThrow(new IOException(MESSAGE));
		expectCloseStreams();
		replayAndExpectErrorMessage(MESSAGE + "\n");
	}

	public void testGrantedValidateIsRequestedThenPassesConvertedRecordsToValidator() throws Exception {
		expectOpenInputAndOutputStreams();
		CONFIGURATION.setValidate(true);
		expectRecordConversionForTwoRecords();
		recordWriter.writeRecords(outputStream, twoRecords);
		expectValidateTwoRecords();
		expectCloseStreams();
		replayMocks();
		converter.execute();
		assertEquals(expectedValidationMessages, errorStream.toString());
	}

	private void expectOpenInputAndOutputStreams() throws IOException {
		expect(streamOpener.openInputStream(CONFIGURATION.getInputFile())).andReturn(inputStream);
		expect(streamOpener.openOutputStream(CONFIGURATION.getOutputFile())).andReturn(outputStream);
	}

	private void expectValidateTwoRecords() {
		expect(validator.validate(twoRecords.get(0))).andReturn(validationMessagesForFirstRecord);
		expect(validator.validate(twoRecords.get(1))).andReturn(validationMessagesForSecondRecord);
	}

	private void expectRecordConversionForTwoRecords() throws IOException {
		expect(recordReader.read(inputStream)).andReturn(twoRecords);
		conversionController.convert(twoRecords.get(0));
		conversionController.convert(twoRecords.get(1));
	}

	private void expectCloseStreams() {
		streamOpener.close(inputStream);
		streamOpener.close(outputStream);
	}
	
	private void replayAndExpectErrorMessage(String message) throws IOException {
		replayMocks();
		converter.execute();
		assertEquals(message, errorStream.toString());
	}
	
	private void replayMocks() {
		replay(recordReader);
		replay(conversionController);
		replay(recordWriter);
		replay(streamOpener);
		replay(validator);
	}

	private Record createRecordWithName(String name) {
		Record record = new Record("label");
		record.setName(name);
		return record;
	}
	
}
