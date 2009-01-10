package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;

public class SubFieldAppender implements FieldConverter {

	private final SubField subField;

	public SubFieldAppender(SubField subField) {
		this.subField = subField;
	}

	public void convert(Field field) {
		field.getSubFields().add(subField);
	}

}
