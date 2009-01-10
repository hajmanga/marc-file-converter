package ppa.marc.converter.generic;

import ppa.marc.converter.FieldConverter;
import ppa.marc.converter.generic.SubFieldTrimmer;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;
import junit.framework.TestCase;

public class SubFieldTrimmerTest extends TestCase {

	Field field = new Field(10, '_', '_');
	FieldConverter converter = new SubFieldTrimmer("abc", " ", ",;");
	
	protected void setUp() throws Exception {
		field.getSubFields().add(new SubField('a', "   123;5;,"));
		field.getSubFields().add(new SubField('d', "   123;5;,"));
		converter.convert(field);
	}
	
	public void testTrimsSubField() throws Exception {
		assertEquals("123;5", field.getSubFields().get(0).getValue());
	}

	public void testDoesNotTrimUnwantedSubField() throws Exception {
		assertEquals("   123;5;,", field.getSubFields().get(1).getValue());
	}

	
}
