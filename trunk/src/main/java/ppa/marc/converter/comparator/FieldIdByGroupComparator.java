package ppa.marc.converter.comparator;

import java.util.Comparator;
import java.util.List;

import ppa.marc.domain.Field;

public class FieldIdByGroupComparator implements Comparator<Field> {

	private final List<List<Integer>> groups;

	public FieldIdByGroupComparator(List<List<Integer>> groups) {
		this.groups = groups;
	}

	public int compare(Field first, Field second) {
		return locateGroupIndex(first) - locateGroupIndex(second);
	}

	private int locateGroupIndex(Field field) {
		for(int i = 0; i < groups.size(); ++i) {
			if(groups.get(i).contains(field.getId())) return i;
		}
		return Integer.MAX_VALUE;
	}

}
