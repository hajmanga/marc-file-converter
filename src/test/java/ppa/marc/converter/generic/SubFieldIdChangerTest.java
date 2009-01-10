package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.converter.generic.SubFieldIdChanger;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;
import junit.framework.TestCase;

public class SubFieldIdChangerTest extends TestCase {

	FieldConverter converter = new SubFieldIdChanger("np", "hi");
	Field field = new Field(400, '0', '_');
	
	protected void setUp() throws Exception {
		field.getSubFields().add(new SubField('n', "data"));
		field.getSubFields().add(new SubField('a', "data"));
		field.getSubFields().add(new SubField('p', "data"));
	}
	
	public void testRenamesSubField() throws Exception {
		converter.convert(field);
		assertEquals(Character.valueOf('h'), field.getSubFields().get(0).getId());
		assertEquals(Character.valueOf('a'), field.getSubFields().get(1).getId());
		assertEquals(Character.valueOf('i'), field.getSubFields().get(2).getId());
	}
	
}
