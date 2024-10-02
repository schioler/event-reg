package test.dk.schioler.cache;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class SystemPropertiesTest {

	@Test
	public void test() {
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			System.out.println("Key="+ key+", value="+ properties.getProperty(key.toString()));
		}
			
		String tmp = System.getProperty("java.io.tmpdir");
		System.out.println(tmp);
		String replace = StringUtils.replace(tmp, "\\", "/");
		System.out.println(replace);
	}

}
