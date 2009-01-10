package ppa.marc.validator.rules.util;

import java.util.List;

import ppa.marc.domain.Field;

public interface SubFieldAnalyser {

	List<Character> analyse(Field field);

}
