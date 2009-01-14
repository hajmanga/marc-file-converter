package ppa.marc;

import java.io.IOException;

import ppa.marc.converter.ConversionController;

public class Main {

	private static final String GENERIC_BEANS_XML = "MarcConverter.xml";
	
	private static SpringBeanLoader<FormatBeanLoader> formatBeanLoader;
	private static BeanLoader<MarcConverter> marcConverterLoader;
	private static Configuration configuration;

	static {
		formatBeanLoader = new SpringBeanLoader<FormatBeanLoader>(GENERIC_BEANS_XML);
		marcConverterLoader = new SpringBeanLoader<MarcConverter>(GENERIC_BEANS_XML);
	}
	
	public static void main(String[] args) {
		ArgumentParser argumentParser = getArgumentParser();
		try {
			configuration = parseAndInjectConfiguration(args, argumentParser, marcConverterLoader);
			injectConversionController();
			injectOutputFormatBasedBean("validatorBeanLoader", "validator");
			injectOutputFormatBasedBean("writerBeanLoader", "writer");
			MarcConverter marcConverter = marcConverterLoader.getBean("marcConverter");
			marcConverter.execute();
		} catch (ParameterException e) {
			System.err.println(e.getMessage());
			if(e.isShowHelp()) System.err.println(argumentParser.getHelp());
		} catch (IOException e) {
			System.err.println("File access failed: " + e.getMessage());
		}
	}

	private static ArgumentParser getArgumentParser() {
		return new SpringBeanLoader<ArgumentParser>(GENERIC_BEANS_XML).getBean("argumentParser");
	}

	private static void injectOutputFormatBasedBean(String loaderBeanName, String injectedName) {
		FormatBeanLoader beanLoader = formatBeanLoader.getBean(loaderBeanName); 
		Object bean = beanLoader.loadBean(configuration.getOutputFormat());
		marcConverterLoader.injectBean(bean, injectedName);
	}

	private static void injectConversionController() {
		FormatBeanLoader converterBeanLoader = formatBeanLoader.getBean("converterBeanLoader"); 
		ConversionController conversionController = (ConversionController) converterBeanLoader.loadBean(configuration.getInputFormat(), configuration.getOutputFormat());
		marcConverterLoader.injectBean(conversionController, "converter");
	}

	private static Configuration parseAndInjectConfiguration(String[] args, ArgumentParser argumentParser, BeanLoader<MarcConverter> converterLoader) throws ParameterException {
		Configuration configuration = argumentParser.parseArgument(args);
		converterLoader.injectBean(configuration, "configuration");
		return configuration;
	}

}
