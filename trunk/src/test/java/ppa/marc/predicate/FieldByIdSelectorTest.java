package ppa.marc.predicate;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.Field;
import ppa.marc.predicate.FieldByIdSelector;

import junit.framework.TestCase;

public class FieldByIdSelectorTest extends TestCase {

	Predicate predicate = new FieldByIdSelector(10);
	
	public void testReturnsFalseWithDifferentId() throws Exception {
		assertFalse(predicate.evaluate(new Field(20)));
	}

	public void testReturnsTrueWithSameId() throws Exception {
		assertTrue(predicate.evaluate(new Field(10)));
	}
	
}
