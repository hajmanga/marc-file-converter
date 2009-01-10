package ppa.marc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TypeBasedFormatBeanLoader implements FormatBeanLoader {

	private final String loaderType;

	public TypeBasedFormatBeanLoader(String loaderType) {
		this.loaderType = loaderType;
	}

	public Object loadBean(MarcFormat format) {
		return getBean(format.toString());
	}

	public Object loadBean(MarcFormat fromFormat, MarcFormat toFormat) {
		return getBean(fromFormat + "-to-" + toFormat);
	}

	private Object getBean(String format) {
		ApplicationContext context = new ClassPathXmlApplicationContext(loaderType + "-" + format + ".xml");
		return context.getBean("formatBean");
	}
	
}
