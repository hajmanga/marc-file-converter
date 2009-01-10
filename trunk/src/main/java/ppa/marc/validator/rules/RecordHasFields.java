package ppa.marc.validator.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import ppa.marc.domain.Record;
import ppa.marc.predicate.FieldByIdSelector;
import ppa.marc.validator.Severity;
import ppa.marc.validator.ValidationMessage;

public class RecordHasFields extends AbstractIdListingRule {

	private final Severity severity;
	private final int[] expectedFields; 
	private static final String VALIDATION_MESSAGE = "Record is missing field";

	public RecordHasFields(Severity severity, int[] expectedFields) {
		this.severity = severity;
		this.expectedFields = expectedFields;
	}

	public List<ValidationMessage> validate(Record record) {
		List<ValidationMessage> result = new ArrayList<ValidationMessage>(1);
		List<Integer> missingFieldIds = new ArrayList<Integer>();
		for(int index = 0; index < expectedFields.length; ++index) {
			if(CollectionUtils.select(record.getFields(), new FieldByIdSelector(expectedFields[index])).isEmpty()) {
				missingFieldIds.add(new Integer(expectedFields[index]));
			}
		}
		if(!missingFieldIds.isEmpty()) result.add(new ValidationMessage(severity, VALIDATION_MESSAGE + getIdListAsString(missingFieldIds) + "."));
		return result;
	}

}
