package ppa.marc.converter.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;

public class FieldRemover implements RecordConverter {

	private final List<Integer> fieldsToRemove;

	public FieldRemover(List<Integer> fieldsToRemove) {
		this.fieldsToRemove = fieldsToRemove;
	}

	public void convert(Record record) {
		final Collection<Field> toBeRemoved = new ArrayList<Field>();
		for(Field field : record.getFields()) {
			if(fieldsToRemove.contains(Integer.valueOf(field.getId()))) {
				toBeRemoved.add(field);
			}
		}
		record.getFields().removeAll(toBeRemoved);
	}
	
}
