package ppa.marc.converter.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ppa.marc.converter.comparator.FieldIdByGroupComparator;
import ppa.marc.converter.generic.FieldGrouper;
import ppa.marc.converter.generic.RecordConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import junit.framework.TestCase;

public class FieldGrouperTest extends TestCase {

	Record record = new Record("id");
	List<List<Integer>> groups = new ArrayList<List<Integer>>();
	RecordConverter converter = new FieldGrouper(new FieldIdByGroupComparator(groups));
	
	protected void setUp() throws Exception {
		record.getFields().add(new Field(100));
		record.getFields().add(new Field(200));
		groups.add(Arrays.asList(new Integer[] { Integer.valueOf(601), Integer.valueOf(606) }));
		groups.add(Arrays.asList(new Integer[] { Integer.valueOf(680) }));
	}
	
	public void testDoesNotChangeOrderIfIdNotInGroup() throws Exception {
		converter.convert(record);
		assertEquals(100, record.getFields().get(0).getId());
		assertEquals(200, record.getFields().get(1).getId());
	}
	
	public void testDoesNotMove680ToLast() throws Exception {
		record.getFields().add(0, new Field(680));
		converter.convert(record);
		assertEquals(680, record.getFields().get(0).getId());
	}
	
	public void testPreservesOrderOf601And606() throws Exception {
		record.getFields().add(0, new Field(606));
		record.getFields().add(1, new Field(601));
		converter.convert(record);
		assertEquals(606, record.getFields().get(0).getId());
		assertEquals(601, record.getFields().get(1).getId());
	}
	
	public void testMoves680After601() throws Exception {
		record.getFields().add(0, new Field(680));
		record.getFields().add(1, new Field(601));
		converter.convert(record);
		assertEquals(601, record.getFields().get(0).getId());
		assertEquals(680, record.getFields().get(1).getId());
	}
	
}
