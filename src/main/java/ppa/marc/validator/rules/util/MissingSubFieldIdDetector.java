package ppa.marc.validator.rules.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ppa.marc.domain.Field;
import ppa.marc.predicate.SubFieldByIdSelector;

public class MissingSubFieldIdDetector implements SubFieldAnalyser {

	private final String expectedSubFieldIds;

	public MissingSubFieldIdDetector(String expectedSubFieldIds) {
		this.expectedSubFieldIds = expectedSubFieldIds;
	}

	public List<Character> analyse(Field field) {
		List<Character> missingSubFieldIds = new ArrayList<Character>();
		for(int i = 0; i < expectedSubFieldIds.length(); ++i) {
			if(CollectionUtils.select(field.getSubFields(), new SubFieldByIdSelector(String.valueOf(expectedSubFieldIds.charAt(i)))).isEmpty()) {
				missingSubFieldIds.add(Character.valueOf(expectedSubFieldIds.charAt(i)));
			}
		}
		return missingSubFieldIds;
	}

}
