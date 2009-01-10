package ppa.marc.validator.rules.util;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.SubField;
import ppa.marc.validator.rules.util.SubFieldsThatContain;
import ppa.marc.validator.rules.util.SubFieldsThatStartWith;

import junit.framework.TestCase;

import static org.easymock.EasyMock.*;

public class SubFieldsThatContainTest extends TestCase {

	private static final String VALUE_TO_BE_CONTAINED = "value";
	private static final String VALUE_DOES_NOT_CONTAIN = "other";
	private static final String VALUE_CONTAINS = "xx" + VALUE_TO_BE_CONTAINED;

	Predicate subFieldByIdSelector = createStrictMock(Predicate.class);

	SubFieldsThatStartWith subFieldsThatStartWith = new SubFieldsThatContain(subFieldByIdSelector, VALUE_TO_BE_CONTAINED);
	
	public void testEvaluateReturnsFalseIfStringDoesNotContainTheValue() throws Exception {
		assertFalse(subFieldsThatStartWith.evaluate(new SubField(VALUE_DOES_NOT_CONTAIN)));
	}

	public void testEvaluateReturnsTrueIfStringContainsTheValue() throws Exception {
		assertTrue(subFieldsThatStartWith.evaluate(new SubField(VALUE_CONTAINS)));
	}
	
}
