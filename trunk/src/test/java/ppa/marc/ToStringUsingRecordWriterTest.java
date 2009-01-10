package ppa.marc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ppa.marc.domain.Record;

import junit.framework.TestCase;

public class ToStringUsingRecordWriterTest extends TestCase {

	OutputStream outputStream = new ByteArrayOutputStream();
	List<Record> records = new ArrayList<Record>();
	Record record = new Record("") {
		public String toString() {
			return "a";
		}
	};
	
	RecordWriter writer = new ToStringUsingRecordWriter();
	
	public void testGrantedEmptyRecordListIsGivenToWriterThenWritesNothing() throws Exception {
		assertOutputIs("");
	}

	public void testGrantedThereIsRecordThenWritesItToOutputStream() throws Exception {
		records.add(record);
		assertOutputIs(record.toString());
	}

	public void testGrantedThereAreTwoRecordsThenWritesThemToOutputStreamSeparatedWithNewline() throws Exception {
		records.add(record);
		records.add(record);
		assertOutputIs(record.toString() + "\n" + record.toString());
	}
	
	private void assertOutputIs(String expectedOutput) throws IOException {
		writer.writeRecords(outputStream, records);
		assertEquals(expectedOutput, outputStream.toString());
	}
	
}
