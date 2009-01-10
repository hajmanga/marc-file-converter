package ppa.marc.converter;

import ppa.marc.domain.Record;

public interface ConversionController {

	void convert(final Record record);

}
