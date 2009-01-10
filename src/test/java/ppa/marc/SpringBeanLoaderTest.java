package ppa.marc;

import junit.framework.TestCase;

public class SpringBeanLoaderTest extends TestCase {

	private static final String EXISTING = "existing";
	private static final String INJECTED = "injected";
	private static final String NON_EXISTING = "non-existing";

	BeanLoader<String> loader = new SpringBeanLoader<String>("existing.xml");
	
	public void testGrantedXmlNotInClasspathThenThrowsRuntimeException() throws Exception {
		try {
		    new SpringBeanLoader<Object>(NON_EXISTING);
			fail();
		} catch(RuntimeException expected) {
		}
	}
	
	public void testGrantedBeanDoesNotExistThenGetBeanThrowsRuntimeException() throws Exception {
		try {
			loader.getBean(NON_EXISTING);
			fail();
		} catch(RuntimeException expected) {
		}
	}
	
	public void testGrantedBeanExistsThenGetBeanReturnsIt() throws Exception {
		assertEquals("abc", loader.getBean(EXISTING).toString());
	}

	public void testGrantedBeanIsInjectedThenInCanBeLoaded() throws Exception {
		loader.injectBean(new String("cab"), INJECTED);
		assertEquals("cab", loader.getBean(INJECTED).toString());
	}
	
}
