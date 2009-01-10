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

public class RecordHasSubFields extends AbstractIdListingRule {

	private final Severity severity;
	private final SubFieldAnalyser missingSubFieldIdDetector;
	private final Predicate fieldByIdSelector;

	public RecordHasSubFields(Severity severity, Predicate fieldByIdSelector, SubFieldAnalyser missingSubFieldIdDetector) {
		this.severity = severity;
		this.fieldByIdSelector = fieldByIdSelector;
		this.missingSubFieldIdDetector = missingSubFieldIdDetector;
	}

	public List<ValidationMessage> validate(Record record) {
		final List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		CollectionUtils.forAllDo(CollectionUtils.select(record.getFields(), fieldByIdSelector), new Closure() {
			public void execute(Object fieldAsObject) {
				final Field field = (Field) fieldAsObject;
				List<Character> missingSubFieldIds = missingSubFieldIdDetector.analyse(field);
				if(!missingSubFieldIds.isEmpty()) {
					messages.add(new ValidationMessage(severity, "Field " + StringUtils.leftPad(String.valueOf(field.getId()), 3, '0') + " should have subfield" + getIdListAsString(missingSubFieldIds) + "."));
				}
			}
		});
		return messages;
	}

}
