package ppa.marc.validator;

import java.util.ArrayList;
import java.util.List;

import ppa.marc.domain.Record;

public class NotSupportedValidator extends Validator {

	public NotSupportedValidator() {
		super(null);
	}

	private static final List<ValidationMessage> validationMessage = new ArrayList<ValidationMessage>();

	static {
		validationMessage.add(new ValidationMessage(Severity.ERROR, "Validation isn't supported for selected output format."));
	}

	public List<ValidationMessage> validate(Record record) {
		return validationMessage;
	}
	
}
