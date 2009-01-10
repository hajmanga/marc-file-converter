package ppa.marc.validator;

public class Severity {

	public static final Severity ERROR = new Severity("ERROR");
	public static final Severity WARNING = new Severity("WARNING");

	private final String severity;

	public String toString() {
		return severity;
	}
	
	private Severity(String severity) {
		this.severity = severity;
	}
	
}
