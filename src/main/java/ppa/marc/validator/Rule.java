package ppa.marc.validator;

import java.util.List;
import ppa.marc.domain.Record;

public interface Rule {

	List<ValidationMessage> validate(Record record);
	
}
