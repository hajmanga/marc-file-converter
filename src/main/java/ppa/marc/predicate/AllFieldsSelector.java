package ppa.marc.predicate;

import org.apache.commons.collections.Predicate;

public class AllFieldsSelector implements Predicate {

	public boolean evaluate(Object object) {
		return true;
	}

}
