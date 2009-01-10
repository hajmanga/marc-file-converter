package ppa.marc;

public interface BeanLoader<BeanClass> {

	BeanClass getBean(String nonExisting);
	void injectBean(Object bean, String beanName);
	
}
