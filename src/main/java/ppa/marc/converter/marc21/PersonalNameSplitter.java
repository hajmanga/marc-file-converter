package ppa.marc.converter.marc21;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import ppa.marc.converter.FieldConverter;
import ppa.marc.domain.Field;
import ppa.marc.domain.SubField;

public class PersonalNameSplitter implements FieldConverter {

	private final Predicate subFieldByIdSelector;
	private final boolean deleteOtherSubfields;

	public PersonalNameSplitter(Predicate subFieldByIdSelector, boolean deleteOtherSubfields) {
		this.subFieldByIdSelector = subFieldByIdSelector;
		this.deleteOtherSubfields = deleteOtherSubfields;
	}

	public void convert(Field field) {
		final List<SubField> unimarcStyleSubFields = new ArrayList<SubField>();
		CollectionUtils.forAllDo(CollectionUtils.select(field.getSubFields(), subFieldByIdSelector), new Closure() {
			public void execute(Object subFieldAsObject) {
				SubField sourceSubField = (SubField) subFieldAsObject;
				convertMarc21NameToUnimarcName(unimarcStyleSubFields, sourceSubField);
			}

			private void convertMarc21NameToUnimarcName(final List<SubField> unimarcStyleSubFields, SubField sourceSubField) {
				String[] splitName = StringUtils.split(sourceSubField.getValue(), ", ");
				unimarcStyleSubFields.add(new SubField('a', splitName[0]));
				unimarcStyleSubFields.add(new SubField('b', splitName[1]));
			}
		});
		if(!deleteOtherSubfields) {
			CollectionUtils.forAllDo(CollectionUtils.selectRejected(field.getSubFields(), subFieldByIdSelector), new Closure() {
				public void execute(Object subFieldAsObject) {
					unimarcStyleSubFields.add((SubField) subFieldAsObject);
				}
			});
		}
		field.getSubFields().clear();
		field.getSubFields().addAll(unimarcStyleSubFields);
	}

}
