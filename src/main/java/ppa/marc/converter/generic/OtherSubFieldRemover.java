package ppa.marc.converter.generic;

import java.util.HashSet;
import java.util.Set;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;

public class OtherSubFieldRemover implements FieldConverter {

	private final String subFieldsToBeKept;

	public OtherSubFieldRemover(String subFieldsToBeKept) {
		this.subFieldsToBeKept = subFieldsToBeKept;
	}

	public void convert(final Field field) {
		final Set<SubField> toBeRemoved = new HashSet<SubField>();
		for(SubField subField : field.getSubFields()) {
			if(subFieldsToBeKept.indexOf(subField.getId()) < 0) {
				toBeRemoved.add(subField);
			}
		}
		field.getSubFields().removeAll(toBeRemoved);
	}

}
