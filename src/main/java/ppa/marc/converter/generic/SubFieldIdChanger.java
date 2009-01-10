package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;

public class SubFieldIdChanger implements FieldConverter {

	private final String fromSubFieldIds;
	private final String toSubFieldIds;

	public SubFieldIdChanger(String fromSubFieldIds, String toSubFieldIds) {
		this.fromSubFieldIds = fromSubFieldIds;
		this.toSubFieldIds = toSubFieldIds;
	}

	public void convert(Field field) {
		for(SubField subField : field.getSubFields()) {
			int matchingSubFieldIdIndex = fromSubFieldIds.indexOf(subField.getId());
			if(matchingSubFieldIdIndex > -1) {
				subField.setId(toSubFieldIds.charAt(matchingSubFieldIdIndex));
			}
		}
	}

}
