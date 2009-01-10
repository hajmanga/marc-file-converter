package ppa.marc.converter.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import ppa.marc.converter.comparator.FieldIdByGroupComparator;
import ppa.marc.domain.Field;

import junit.framework.TestCase;

public class FieldIdByGroupComparatorTest extends TestCase {

	List<List<Integer>> groups = new ArrayList<List<Integer>>();
	Comparator<Field> comparator = new FieldIdByGroupComparator(groups);
	
	protected void setUp() throws Exception {
		groups.add(Arrays.asList(new Integer[] { Integer.valueOf(1) }));
		groups.add(Arrays.asList(new Integer[] { Integer.valueOf(2) }));
	}

	public void testThreeIsGreaterThanTwo() throws Exception {
		assertTrue(comparator.compare(new Field(3), new Field(2)) > 0);
	}
	
	public void testTwoIsLessThanThree() throws Exception {
		assertTrue(comparator.compare(new Field(2), new Field(3)) < 0);
	}

	public void testOneEqualsOne() throws Exception {
		assertTrue(comparator.compare(new Field(1), new Field(1)) == 0);
	}

	public void testOneIsLessThanTwo() throws Exception {
		assertTrue(comparator.compare(new Field(1), new Field(2)) < 0);
	}
	
}
