package ppa.marc.predicate;

import org.apache.commons.collections.Predicate;

import ppa.marc.predicate.AllFieldsSelector;

import junit.framework.TestCase;

public class AllFieldsSelectorTest extends TestCase {

	Predicate predicate = new AllFieldsSelector();
	
	public void testEvaluateReturnsAlwaysTrue() throws Exception {
		assertTrue(predicate.evaluate(null));
	}
	
}
