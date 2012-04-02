package ppa.marc.validator.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import ppa.marc.validator.Rule;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;

public class FieldIsUnique implements Rule {

	private final Predicate fieldByIdPredicate;
	private final Severity severity;

	public FieldIsUnique(Severity severity, Predicate fieldByIdPredicate) {
		this.severity = severity;
		this.fieldByIdPredicate = fieldByIdPredicate;
	}

	public List<ValidationMessage> validate(Record record) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		@SuppressWarnings("unchecked")
		Collection<Field> fields = CollectionUtils.select(record.getFields(), fieldByIdPredicate);
		if(fields.size() > 1) messages.add(createValidationMessage(fields));
		return messages;
	}

	private ValidationMessage createValidationMessage(Collection<Field> fields) {
		return new ValidationMessage(severity, "There are " + fields.size() + " instances of field " + extractFieldIdAsString(fields) + "; expected one.");
	}

	private String extractFieldIdAsString(Collection<Field> fields) {
		return StringUtils.leftPad(String.valueOf(((Field) fields.iterator().next()).getId()), 3, '0');
	}

}
