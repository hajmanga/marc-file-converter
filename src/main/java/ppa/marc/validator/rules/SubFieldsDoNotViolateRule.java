package ppa.marc.validator.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import ppa.marc.domain.Field;
import ppa.marc.domain.Record;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;
import ppa.marc.validator.rules.util.SubFieldAnalyser;

public class SubFieldsDoNotViolateRule extends AbstractIdListingRule {

	private final Predicate fieldByIdPredicate;
	private final SubFieldAnalyser subFieldsThatViolateRule;
	private final Severity severity;
	private final String message;

	public SubFieldsDoNotViolateRule(String message, Severity severity, Predicate fieldByIdPredicate, SubFieldAnalyser subFieldsThatViolateRule) {
		this.message = message;
		this.severity = severity;
		this.fieldByIdPredicate = fieldByIdPredicate;
		this.subFieldsThatViolateRule = subFieldsThatViolateRule;
	}

	public List<ValidationMessage> validate(Record record) {
		final ArrayList<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
		CollectionUtils.forAllDo(CollectionUtils.select(record.getFields(), fieldByIdPredicate), new Closure() {
			public void execute(Object fieldAsObject) {
				Field field = (Field) fieldAsObject;
				List<Character> subFieldIds = subFieldsThatViolateRule.analyse(field);
				if(subFieldIds.size() > 0) {
					validationMessages.add(new ValidationMessage(severity, message + " field " + StringUtils.leftPad(String.valueOf(field.getId()), 3, '0') + "'s subfield" + getIdListAsString(subFieldIds) + "."));
				}
			}
		});
		return validationMessages;
	}

}
