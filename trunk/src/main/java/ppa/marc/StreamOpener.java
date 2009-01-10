package ppa.marc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamOpener {

	public OutputStream openOutputStream(File outputFile) throws IOException {
		if(outputFile == null) return System.out;
		return new FileOutputStream(outputFile);
	}

	public InputStream openInputStream(File inputFile) throws IOException {
		return new FileInputStream(inputFile);
	}

	public void close(OutputStream outputStream) {
		try {
			outputStream.close();
		} catch(Exception ignoredOnPurpose) {
		}
	}

	public void close(InputStream inputStream) {
		try {
			inputStream.close();
		} catch(Exception ignoredOnPurpose) {
		}
	}

	public OutputStream getErrorStream() {
		return System.err;
	}

}
