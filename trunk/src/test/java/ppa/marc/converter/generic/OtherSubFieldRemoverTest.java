package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.converter.generic.OtherSubFieldRemover;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;
import junit.framework.TestCase;

public class OtherSubFieldRemoverTest extends TestCase {

	private static final SubField SUB_FIELD_A = new SubField('a', "data");
	private static final SubField SUB_FIELD_B = new SubField('b', "data");
	
	FieldConverter converter = new OtherSubFieldRemover("a");
	
	Field field = new Field(10);
	
	protected void setUp() throws Exception {
		field.getSubFields().add(SUB_FIELD_A);
		field.getSubFields().add(SUB_FIELD_B);
	}
	
	public void testLeavesSpecifiedSubField() throws Exception {
		converter.convert(field);
		assertEquals(SUB_FIELD_A, field.getSubFields().get(0));
	}
	
	public void testRemovesOtherSubField() throws Exception {
		converter.convert(field);
		assertEquals(1, field.getSubFields().size());
	}
	
}
