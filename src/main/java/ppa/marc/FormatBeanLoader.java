package ppa.marc;

public interface FormatBeanLoader {

	Object loadBean(MarcFormat format);

	Object loadBean(MarcFormat fromFormat, MarcFormat toFormat);
	
}
