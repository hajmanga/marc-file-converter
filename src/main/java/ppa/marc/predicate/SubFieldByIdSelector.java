package ppa.marc.predicate;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.SubField;

public class SubFieldByIdSelector implements Predicate {

	private final String allowedSubFieldIds;

	public SubFieldByIdSelector(String allowedSubFieldIds) {
		this.allowedSubFieldIds = allowedSubFieldIds;
	}

	public boolean evaluate(Object object) {
		SubField subField = (SubField) object;
		if(allowedSubFieldIds.contains(String.valueOf(subField.getId()))) return true;
		return false;
	}

}
