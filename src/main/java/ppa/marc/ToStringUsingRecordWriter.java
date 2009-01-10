package ppa.marc;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import ppa.marc.domain.Record;

public class ToStringUsingRecordWriter implements RecordWriter {

	public void writeRecords(OutputStream outputStream, List<Record> records) throws IOException {
		boolean notFirstRecord = false;
		for(Record record : records) {
			if(notFirstRecord) {
				outputStream.write('\n');
			}
			outputStream.write(record.toString().getBytes());
			notFirstRecord = true;
		}
	}

}
