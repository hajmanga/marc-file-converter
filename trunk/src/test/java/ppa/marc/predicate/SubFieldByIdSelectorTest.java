package ppa.marc.predicate;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.SubField;
import ppa.marc.predicate.SubFieldByIdSelector;
import junit.framework.TestCase;

public class SubFieldByIdSelectorTest extends TestCase {

	Predicate predicate = new SubFieldByIdSelector("a");
	
	public void testReturnsFalseWithDifferentId() throws Exception {
		assertFalse(predicate.evaluate(new SubField('b', "data")));
	}

	public void testReturnsTrueWithSameId() throws Exception {
		assertTrue(predicate.evaluate(new SubField('a', "data")));
	}
	
}
