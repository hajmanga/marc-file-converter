package ppa.marc;

import java.util.HashMap;
import java.util.Map;

public class MarcFormat {

	public static final MarcFormat MARC21 = new MarcFormat("MARC21");
	public static final MarcFormat UNIMARC_TEXT = new MarcFormat("UNIMARC_TEXT");
	public static final MarcFormat MARC21_TEXT = new MarcFormat("MARC21_TEXT");

	private static final Map<String, MarcFormat> formats = new HashMap<String, MarcFormat>();
	private final String format;

	static {
		formats.put("MARC21", MARC21);
		formats.put("MARC21_TEXT", MARC21_TEXT);
		formats.put("UNIMARC_TEXT", UNIMARC_TEXT);
	}
	
	protected MarcFormat(String format) {
		this.format = format;
	}
	
	public static MarcFormat fromString(String formatAsString) {
		String formatInUpperCase = formatAsString.toUpperCase();
		if(formats.containsKey(formatInUpperCase)) return formats.get(formatInUpperCase);
		throw new IllegalArgumentException("Unsupported format: " + formatAsString);
	}

	public String toString() {
		return format;
	}
	
}
