package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;

public class FieldIdChanger implements FieldConverter {

	private final int newId;
	private final Character newFirstIndicator;
	private final Character newSecondIndicator;
	private final boolean isControlField;
	
	public FieldIdChanger(int newId) {
		this.newId = newId;
		this.newFirstIndicator = null;
		this.newSecondIndicator = null;
		this.isControlField = true;
	}

	public FieldIdChanger(int newId, char newFirstIndicator, char newSecondIndicator) {
		this.newId = newId;
		this.newFirstIndicator = newFirstIndicator;
		this.newSecondIndicator = newSecondIndicator;
		this.isControlField = false;
	}

	public void convert(Field field) {
		field.setId(newId);
		if(!isControlField) {
			field.setFirstIndicator(newFirstIndicator);
			field.setSecondIndicator(newSecondIndicator);
		}
		field.setIsControlField(isControlField);
	}

}
