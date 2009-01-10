package ppa.marc.validator;

import org.apache.commons.lang.builder.EqualsBuilder;

public class ValidationMessage {

	private final String message;
	private final Severity severity;

	public ValidationMessage(Severity severity, String message) {
		this.severity = severity;
		this.message = message;
	}

	public Severity getSeverity() {
		return severity;
	}

	public String getMessage() {
		return message;
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof ValidationMessage)) return false;
		ValidationMessage other = (ValidationMessage) obj;
		return new EqualsBuilder().append(severity, other.severity).append(message, other.message).isEquals();
	}

	public String toString() {
		return severity.toString() + ": " + message;
	}
	
}
