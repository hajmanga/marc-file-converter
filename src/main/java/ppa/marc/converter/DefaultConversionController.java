package ppa.marc.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ppa.marc.converter.generic.RecordConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.Record;

public class DefaultConversionController implements ConversionController {

	private final List<RecordConverter> preRecordConverters;
	private final Map<Integer, List<FieldConverter>> fieldConverters;
	private final List<RecordConverter> postRecordConverters;

	public DefaultConversionController(List<RecordConverter> preRecordConverters, Map<Integer, List<FieldConverter>> fieldConverters, List<RecordConverter> postRecordConverters) {
		this.preRecordConverters = preRecordConverters;
		this.fieldConverters = fieldConverters;
		this.postRecordConverters = postRecordConverters;
	}

	public void convert(final Record record) {
		executeConvertersOnRecord(preRecordConverters, record);
		executeConvertersOnFields(record);
		executeConvertersOnRecord(postRecordConverters, record);
	}

	private void executeConvertersOnFields(final Record record) {
		for(Field field : record.getFields()) {
			executeConvertersOnField(field);
		}
	}

	private void executeConvertersOnField(final Field field) {
		for(FieldConverter fieldConverter : getConvertersOfField(field)) {
			fieldConverter.convert(field);
		}
	}

	private List<FieldConverter> getConvertersOfField(final Field field) {
		List<FieldConverter> converters = fieldConverters.get(Integer.valueOf(field.getId()));
		if(converters == null) converters = new ArrayList<FieldConverter>();
		return converters;
	}

	private void executeConvertersOnRecord(List<RecordConverter> recordConverters, final Record record) {
		for(RecordConverter recordConverter : recordConverters) {
			recordConverter.convert(record);
		}
	}

}
