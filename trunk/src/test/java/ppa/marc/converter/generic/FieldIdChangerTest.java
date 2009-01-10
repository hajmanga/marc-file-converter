package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.converter.generic.FieldIdChanger;
import ppa.marc.domain.Field;
import junit.framework.TestCase;

public class FieldIdChangerTest extends TestCase {

	Field field = new Field(10, '1', '2');
	
	public void testIdIsChanged() throws Exception {
		FieldConverter converter = new FieldIdChanger(20);
		converter.convert(field);
		assertEquals(20, field.getId());
		assertEquals('1', field.getFirstIndicator());
		assertEquals('2', field.getSecondIndicator());
	}
	
	public void testIdAndIndicatorsAreChangedIfSpecifiedInConstructor() throws Exception {
		FieldConverter converter = new FieldIdChanger(20, 'a', 'b');
		converter.convert(field);
		assertEquals(20, field.getId());
		assertEquals('a', field.getFirstIndicator());
		assertEquals('b', field.getSecondIndicator());
	}
	
}
