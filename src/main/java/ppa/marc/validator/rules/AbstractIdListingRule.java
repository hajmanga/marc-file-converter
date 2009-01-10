package ppa.marc.validator.rules;

import java.util.List;

import ppa.marc.validator.Rule;

abstract public class AbstractIdListingRule implements Rule {

	private int position;
	
	String getIdListAsString(final List<? extends Object> missingSubFieldIds) {
		position = 0;
		final StringBuffer idList = new StringBuffer();
		if(missingSubFieldIds.size() > 1) idList.append('s');
		for(Object id : missingSubFieldIds) {
			if(position > 0) {
				appendAndOrComma(missingSubFieldIds, idList);
			}
			idList.append(" " + id);
			position++;
		}
		return idList.toString();
	}

	private void appendAndOrComma(final List<? extends Object> missingSubFieldIds, final StringBuffer idList) {
		if(position == missingSubFieldIds.size()-1) {
			idList.append(" and");
		}
		else {
			idList.append(',');
		}
	}
	
}
