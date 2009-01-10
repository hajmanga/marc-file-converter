package ppa.marc.predicate;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.Field;

public class FieldByIdSelector implements Predicate {

	private final int id;
	
	public FieldByIdSelector(int id) {
		this.id = id;
	}

	public boolean evaluate(Object object) {
		if(((Field) object).getId() == id) return true;
		return false;
	}

}
