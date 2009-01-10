package ppa.marc.validator.rules.util;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.SubField;

public class SubFieldsThatContain extends SubFieldsThatStartWith {

	public SubFieldsThatContain(Predicate subFieldByIdSelector, String containsValue) {
		super(subFieldByIdSelector, containsValue);
	}
	
	protected boolean evaluate(SubField subField) {
		return subField.getValue().contains(searchString);
	}

}
