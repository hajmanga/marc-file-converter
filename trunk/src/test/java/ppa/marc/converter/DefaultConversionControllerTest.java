package ppa.marc.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ppa.marc.converter.DefaultConversionController;
import ppa.marc.converter.FieldConverter;
import ppa.marc.converter.generic.RecordConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.Record;

import static org.easymock.EasyMock.*;

import junit.framework.TestCase;

public class DefaultConversionControllerTest extends TestCase {

	private static final int FIELD_WITHOUT_CONVERTERS = 300;
	RecordConverter preRecordConverter = createStrictMock(RecordConverter.class);
	RecordConverter postRecordConverter = createStrictMock(RecordConverter.class);

	List<RecordConverter> preRecordConverters = new ArrayList<RecordConverter>();
	List<RecordConverter> postRecordConverters = new ArrayList<RecordConverter>();
	
	FieldConverter[] fieldConverter = new FieldConverter[] {
			createStrictMock(FieldConverter.class),
			createStrictMock(FieldConverter.class)
	};
	Map<Integer,List<FieldConverter>> fieldConverters = new HashMap<Integer,List<FieldConverter>>();

	Record record = new Record("id");
	Field field = new Field(100);
	
	ConversionController controller = new DefaultConversionController(preRecordConverters, fieldConverters, postRecordConverters);
	
	protected void setUp() throws Exception {
		record.getFields().add(field);
		preRecordConverters.add(preRecordConverter);
		postRecordConverters.add(postRecordConverter);
		fieldConverters.put(Integer.valueOf(100), createFieldConverterList(fieldConverter[0]));
		fieldConverters.put(Integer.valueOf(200), createFieldConverterList(fieldConverter[1]));
	}

	private List<FieldConverter> createFieldConverterList(FieldConverter converter) {
		List<FieldConverter> fieldConverters = new ArrayList<FieldConverter>();
		fieldConverters.add(converter);
		return fieldConverters;
	}
	
	public void testControllerCallsRecordConvertersOnTheRecord() throws Exception {
		preRecordConverter.convert(record);
		postRecordConverter.convert(record);
		replay(preRecordConverter);
		replay(postRecordConverter);
		controller.convert(record);
		verify(preRecordConverter);
		verify(postRecordConverter);
	}
	
	public void testControllerCallsFieldSpecificConverter() throws Exception {
		expectConversionOfField100();
		controller.convert(record);
		verifyFieldMocks();
	}

	public void testGrantedFieldHasNoConversionControllerThenConvertDoesNothing() throws Exception {
		field.setId(FIELD_WITHOUT_CONVERTERS);
		replayFieldMocks();
		controller.convert(record);
		verifyFieldMocks();
	}
	
	private void replayFieldMocks() {
		replay(fieldConverter[0]);
		replay(fieldConverter[1]);
	}
	
	private void verifyFieldMocks() {
		verify(fieldConverter[0]);
		verify(fieldConverter[1]);
	}

	private void expectConversionOfField100() {
		fieldConverter[0].convert(field);
		replayFieldMocks();
	}

}
