package ppa.marc;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MarcFormat {

	public static final MarcFormat MARC21 = new MarcFormat("MARC21");
	public static final MarcFormat UNIMARC_TEXT = new MarcFormat("UNIMARC_TEXT");
	public static final MarcFormat MARC21_TEXT = new MarcFormat("MARC21_TEXT");
	public static final MarcFormat UNIMARC = new MarcFormat("UNIMARC");

	private static final Map<String, MarcFormat> outputFormats = new LinkedHashMap<String, MarcFormat>();
	private static final Map<String, MarcFormat> inputFormats = new LinkedHashMap<String, MarcFormat>();
	
	private final String format;

	static {
		outputFormats.put("MARC21_TEXT", MARC21_TEXT);
		outputFormats.put("UNIMARC", UNIMARC);
		outputFormats.put("UNIMARC_TEXT", UNIMARC_TEXT);
		inputFormats.put("MARC21", MARC21);
		inputFormats.put("UNIMARC", UNIMARC);
	}
	
	protected MarcFormat(String format) {
		this.format = format;
	}
	
	public String toString() {
		return format;
	}

	public static MarcFormat inputFormatFromString(String inputFormat) throws ParameterException {
		return parseFormat(inputFormat, "Input", inputFormats);
	}

	public static MarcFormat outputFormatFromString(String outputFormat) throws ParameterException {
		return parseFormat(outputFormat, "Output", outputFormats);
	}

	private static MarcFormat parseFormat(String inputFormat, String formatType, Map<String, MarcFormat> formats) throws ParameterException {
		String formatInUpperCase = inputFormat.toUpperCase();
		if(formats.containsKey(formatInUpperCase)) return formats.get(formatInUpperCase);
		throw new ParameterException(formatType + " format '" + inputFormat + "' isn't supported; supported " + formatType.toLowerCase() + " format" + getSupportedFormatList(formats));
	}

	private static String getSupportedFormatList(Map<String, MarcFormat> formats) {
		StringBuilder formatList = new StringBuilder();
		Set<String> keys = formats.keySet();
		if(keys.size() == 1) {
			formatList.append(" is ");
		} 
		else {
			formatList.append("s are ");
		}
		makeReadableString(formatList, keys);
		formatList.append('.');
		return formatList.toString();
	}

	private static void makeReadableString(StringBuilder formatList, Set<String> keys) {
		Iterator<String> it = keys.iterator();
		for(int position = 0; position < keys.size(); ++position) {
			if(position > 0) {
				if(position+1 < keys.size()) formatList.append(", ");
				else formatList.append(" and ");
			}
			formatList.append(it.next());
		}
	}
	
}
