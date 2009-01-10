package ppa.marc.converter.generic;

import org.apache.commons.lang.StringUtils;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;

public class SubFieldTrimmer implements FieldConverter {

	private final String rightSideTrimCharacters;
	private final String leftSideTrimCharacters;
	private final String subFieldIds;

	public SubFieldTrimmer(String subFieldIds, String leftSideTrimCharacters, String rightSideTrimCharacters) {
		this.subFieldIds = subFieldIds;
		this.leftSideTrimCharacters = leftSideTrimCharacters;
		this.rightSideTrimCharacters = rightSideTrimCharacters;
	}

	public void convert(Field field) {
		for(SubField subField : field.getSubFields()) {
			if(subFieldIds.indexOf(subField.getId()) > -1) {
				subField.setValue(StringUtils.stripStart(StringUtils.stripEnd(subField.getValue(), rightSideTrimCharacters), leftSideTrimCharacters));
			}
		}
	}

}
