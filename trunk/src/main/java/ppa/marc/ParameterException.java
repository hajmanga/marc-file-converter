package ppa.marc;

public class ParameterException extends Exception {

	private static final long serialVersionUID = 1L;
	private final boolean showHelp;
	
	public ParameterException(String message) {
		super(message);
		showHelp = false;
	}

	public ParameterException(String message, boolean showHelp) {
		super(message);
		this.showHelp = showHelp;
	}

	public boolean isShowHelp() {
		return showHelp;
	}

}
