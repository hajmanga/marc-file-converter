package ppa.marc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanLoader<BeanClass> implements BeanLoader<BeanClass> {

	private final ClassPathXmlApplicationContext context;

	public SpringBeanLoader(String springBeanConfigurationXml) {
		context = new ClassPathXmlApplicationContext(springBeanConfigurationXml);
	}

	@SuppressWarnings("unchecked")
	public BeanClass getBean(String nonExisting) {
		return (BeanClass) context.getBean(nonExisting);
	}

	public void injectBean(Object bean, String beanName) {
		context.getBeanFactory().registerSingleton(beanName, bean);
	}

}
