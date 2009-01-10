package ppa.marc.converter.generic;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import ppa.marc.domain.SubField;

public class FieldCopier implements RecordConverter {

	private final int destinationId;
	private final Predicate fieldByIdPredicate;
	private final Predicate subFieldByIdPredicate;
	private final char secondIndicator;
	private final char firstIndicator;

	public FieldCopier(Predicate fieldByIdPredicate, Predicate subFieldByIdPredicate, int destinationId, char firstIndicator, char secondIndicator) {
		this.fieldByIdPredicate = fieldByIdPredicate;
		this.subFieldByIdPredicate = subFieldByIdPredicate;
		this.destinationId = destinationId;
		this.firstIndicator = firstIndicator;
		this.secondIndicator = secondIndicator;
	}

	public void convert(final Record record) {
		CollectionUtils.forAllDo(CollectionUtils.select(record.getFields(), fieldByIdPredicate), new Closure() {
			public void execute(Object object) {
				Field destinationField = new Field(destinationId, firstIndicator, secondIndicator);
				processSubFields((Field) object, destinationField);
				record.getFields().add(destinationField);
			}
			private void processSubFields(Field sourceField, final Field destinationField) {
				CollectionUtils.forAllDo(CollectionUtils.select(sourceField.getSubFields(), subFieldByIdPredicate), new Closure() {
					public void execute(Object subFieldAsObject) {
						SubField source = (SubField) subFieldAsObject;
						destinationField.getSubFields().add(new SubField(source.getId(), source.getValue()));
					}
				});
			}
		});
	}
	
}
