package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;

public class FieldIdChanger implements FieldConverter {

	private final int newId;
	private final Character newFirstIndicator;
	private final Character newSecondIndicator;

	public FieldIdChanger(int newId) {
		this.newId = newId;
		this.newFirstIndicator = null;
		this.newSecondIndicator = null;
	}

	public FieldIdChanger(int newId, char newFirstIndicator, char newSecondIndicator) {
		this.newId = newId;
		this.newFirstIndicator = newFirstIndicator;
		this.newSecondIndicator = newSecondIndicator;
	}

	public void convert(Field field) {
		field.setId(newId);
		if(newFirstIndicator != null) field.setFirstIndicator(newFirstIndicator);
		if(newSecondIndicator != null) field.setSecondIndicator(newSecondIndicator);
	}

}
