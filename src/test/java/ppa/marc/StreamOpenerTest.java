package ppa.marc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import junit.framework.TestCase;
import static org.easymock.classextension.EasyMock.*;

public class StreamOpenerTest extends TestCase {

	OutputStream outputStream = createStrictMock(OutputStream.class);
	InputStream inputStream = createStrictMock(InputStream.class);
	
	StreamOpener streamOpener = new StreamOpener();

	protected void tearDown() throws Exception {
		verify(outputStream);
		verify(inputStream);
	}
	
	public void testGrantedOutputFileIsNullThenOpenOutputReturnsSystemOut() throws Exception {
		replayMocks();
		assertEquals(System.out, streamOpener.openOutputStream(null));
	}
	
	public void testGrantedOutputStreamIsGivenToCloseThenClosesTheStream() throws Exception {
		outputStream.close();
		replayMocks();
		streamOpener.close(outputStream);
	}

	public void testGrantedInputStreamIsGivenToCloseThenClosesTheStream() throws Exception {
		inputStream.close();
		replayMocks();
		streamOpener.close(inputStream);
	}

	public void testGrantedCloseOutputThrowsExceptionThenItIsNotThrownUpwards() throws Exception {
		outputStream.close();
		expectLastCall().andThrow(new IOException());
		replayMocks();
		streamOpener.close(outputStream);
	}

	public void testGrantedCloseInputThrowsExceptionThenItIsNotThrownUpwards() throws Exception {
		inputStream.close();
		expectLastCall().andThrow(new IOException());
		replayMocks();
		streamOpener.close(inputStream);
	}
	
	public void testAssertOpenErrorStreamReturnsSystemErr() throws Exception {
		replayMocks();
		assertEquals(System.err, streamOpener.getErrorStream());
	}
	
	private void replayMocks() {
		replay(outputStream);
		replay(inputStream);
	}
	
}
