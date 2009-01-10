package ppa.marc.converter.generic;

import java.util.Arrays;
import java.util.Comparator;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;

public class FieldGrouper implements RecordConverter {

	private final Comparator<Field> fieldComparator;

	public FieldGrouper(Comparator<Field> fieldComparator) {
		this.fieldComparator = fieldComparator;
	}

	public void convert(Record record) {
		Field[] fieldsToBeSorted = record.getFields().toArray(new Field[0]); 
		Arrays.sort(fieldsToBeSorted, fieldComparator);
		record.getFields().clear();
		record.getFields().addAll(Arrays.asList(fieldsToBeSorted));
	}

}
