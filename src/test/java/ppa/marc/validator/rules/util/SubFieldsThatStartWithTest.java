package ppa.marc.validator.rules.util;

import static org.easymock.EasyMock.*;

import java.util.List;

import org.apache.commons.collections.Predicate;

import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;
import ppa.marc.validator.rules.util.SubFieldAnalyser;
import ppa.marc.validator.rules.util.SubFieldsThatStartWith;
import junit.framework.TestCase;

public class SubFieldsThatStartWithTest extends TestCase {

	private static final char MATCHING_ID = 'a';
	private static final SubField SUB_FIELD_MATCH = new SubField(MATCHING_ID, "The Prefix");
	private static final SubField SUB_FIELD_NO_MATCH = new SubField(MATCHING_ID, "Not The Prefix");
	private static final SubField SUB_FIELD_DIFFERENT_ID = new SubField('b', "The Prefix");

	Predicate subFieldByIdSelector = createStrictMock(Predicate.class);
	Field field = new Field(10);

	SubFieldAnalyser analyser = new SubFieldsThatStartWith(subFieldByIdSelector, "The ");

	protected void tearDown() throws Exception {
		verify(subFieldByIdSelector);
	}
	
	public void testReturnsNothingIfFieldDoesNotHaveSubFields() throws Exception {
		replay(subFieldByIdSelector);
		assertEquals(0, analyser.analyse(field).size());
	}
	
	public void testReturnsNothingIfSubFieldDoesNotStartWithThePrefix() throws Exception {
		addSubField(SUB_FIELD_NO_MATCH, true);
		replay(subFieldByIdSelector);
		assertEquals(0, analyser.analyse(field).size());
	}
	
	public void testReturnsSubFieldIdIfStartsWithThePrefix() throws Exception {
		addSubField(SUB_FIELD_MATCH, true);
		replay(subFieldByIdSelector);
		assertEquals(Character.valueOf(MATCHING_ID), analyser.analyse(field).get(0));
	}

	public void testReturnsOnlyTheMatchingSubFieldId() throws Exception {
		addSubField(SUB_FIELD_MATCH, true);
		addSubField(SUB_FIELD_DIFFERENT_ID, false);
		replay(subFieldByIdSelector);
		List<Character> result = analyser.analyse(field);
		assertEquals(Character.valueOf(MATCHING_ID), result.get(0));
		assertEquals(1, result.size());
	}

	private void addSubField(SubField subField, boolean hasExpectedId) {
		expect(subFieldByIdSelector.evaluate(subField)).andReturn(hasExpectedId);
		field.getSubFields().add(subField);
	}
	
}
