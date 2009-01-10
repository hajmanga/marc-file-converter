package ppa.marc.validator.rules.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;

public class SubFieldsThatStartWith implements SubFieldAnalyser {

	protected final String searchString;
	private final Predicate subFieldByIdSelector;

	public SubFieldsThatStartWith(Predicate subFieldByIdSelector, String startOfTheValue) {
		this.subFieldByIdSelector = subFieldByIdSelector;
		this.searchString = startOfTheValue;
	}

	public List<Character> analyse(Field field) {
		final List<Character> subFieldIdsWithThePrefix = new ArrayList<Character>();
		CollectionUtils.forAllDo(CollectionUtils.select(field.getSubFields(), subFieldByIdSelector), new Closure() {
			public void execute(Object subFieldAsObject) {
				SubField subField = (SubField) subFieldAsObject;
				if(evaluate(subField)) {
					subFieldIdsWithThePrefix.add(subField.getId());
				}
			}
		});
		return subFieldIdsWithThePrefix;
	}

	protected boolean evaluate(SubField subField) {
		return subField.getValue().startsWith(searchString);
	}
	
}
