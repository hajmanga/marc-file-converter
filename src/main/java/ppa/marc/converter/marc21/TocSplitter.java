package ppa.marc.converter.marc21;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;

public class TocSplitter implements FieldConverter {

	private final Predicate subFieldByIdSelector;

	public TocSplitter(Predicate subFieldByIdSelector) {
		this.subFieldByIdSelector = subFieldByIdSelector;
	}
	
	public void convert(Field field) {
		@SuppressWarnings("unchecked")
		Collection<SubField> subFields = CollectionUtils.select(field.getSubFields(), subFieldByIdSelector);
		if(subFields.isEmpty()) return;
		if(subFields.size() > 1) throw new RuntimeException("Field " + field.getId() + " has more than one 'a' subfield.");
		convertSingleSubFieldToMany(field, subFields);
	}

	private void convertSingleSubFieldToMany(Field field, Collection<SubField> subFields) {
		field.getSubFields().clear();
		String[] tocEntries = StringUtils.splitByWholeSeparator(((SubField) subFields.iterator().next()).getValue(), " -- ");
		for(int i = 0; i < tocEntries.length; ++i) {
			field.getSubFields().add(new SubField('a', tocEntries[i]));
		}
	}

}
