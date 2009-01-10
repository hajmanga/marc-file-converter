package ppa.marc.converter;

import ppa.marc.domain.Record;
import junit.framework.TestCase;
import static org.easymock.classextension.EasyMock.*;

public class EmptyConversionControllerTest extends TestCase {

	ConversionController controller = new EmptyConversionController();
	Record record = createStrictMock(Record.class);
	
	public void testGrantedConvertIsCalledThenRecordRemainsUnchanged() throws Exception {
		replay(record);
		controller.convert(record);
		verify(record);
	}
	
}
