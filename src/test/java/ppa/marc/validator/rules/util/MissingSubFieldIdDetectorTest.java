package ppa.marc.validator.rules.util;

import java.util.ArrayList;
import java.util.List;

import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;
import ppa.marc.validator.rules.util.MissingSubFieldIdDetector;
import ppa.marc.validator.rules.util.SubFieldAnalyser;

import junit.framework.TestCase;

public class MissingSubFieldIdDetectorTest extends TestCase {

	SubFieldAnalyser missingSubFieldIdDetector = new MissingSubFieldIdDetector("ab");
	Field field = new Field(200, '_', '1');
	SubField[] subFields = new SubField[] {
			new SubField('a', "data"),
			new SubField('b', "data")
	};
	
	protected void setUp() throws Exception {
		for(int i = 0; i < subFields.length; ++i) {
			field.getSubFields().add(subFields[i]);
		}
	}
	
	public void testReturnsNothingIfExpectedSubFieldsAreInField() throws Exception {
		assertEquals(0, missingSubFieldIdDetector.analyse(field).size());
	}
	
	public void testReturnsIdOfMissingSubField() throws Exception {
		field.getSubFields().remove(1);
		List<Character> expectedSubFieldIds = new ArrayList<Character>();
		expectedSubFieldIds.add(Character.valueOf('b'));
		assertEquals(expectedSubFieldIds, missingSubFieldIdDetector.analyse(field));
	}
	
}
