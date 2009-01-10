package ppa.marc.converter.generic;

import java.util.ArrayList;
import java.util.List;

import ppa.marc.converter.generic.FieldRemover;
import ppa.marc.converter.generic.RecordConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.Record;

import junit.framework.TestCase;

public class FieldRemoverTest extends TestCase {

	List<Integer> fieldsToRemove = new ArrayList<Integer>();
	RecordConverter converter = new FieldRemover(fieldsToRemove);
	Record record = new Record("id");
	Field field = new Field(10);
	
	protected void setUp() throws Exception {
		record.getFields().add(field);
	}
	
	public void testRemovesNothingIfListIsEmpty() throws Exception {
		converter.convert(record);
		assertTrue(record.getFields().contains(field));
	}
	
	public void testRemovesSpecifiedField() throws Exception {
		fieldsToRemove.add(Integer.valueOf(10));
		converter.convert(record);
		assertFalse(record.getFields().contains(field));
	}
	
}
