package ppa.marc.validator;

import java.util.ArrayList;
import java.util.List;

import ppa.marc.domain.Record;

public class Validator {

	private final List<Rule> rules;

	public Validator(List<Rule> rules) {
		this.rules = rules;
	}

	public List<ValidationMessage> validate(final Record record) {
		final List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
		for(Rule rule : rules) {
			validationMessages.addAll(rule.validate(record));
		}
		return validationMessages;
	}

}
