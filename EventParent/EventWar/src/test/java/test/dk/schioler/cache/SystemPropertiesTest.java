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

	
	/*
	 * 
	 * 
	 * 
	 * C:\Users\lars\eclipse-workspace\EventWar\target\test-classes;C:\Users\lars\eclipse-workspace\EventWar\target\classes;C:\Users\lars\eclipse-workspace\EventBase\target\classes;C:\Users\lars\.m2\repository\org\springframework\spring-jdbc\6.1.3\spring-jdbc-6.1.3.jar;C:\Users\lars\.m2\repository\org\springframework\spring-tx\6.1.3\spring-tx-6.1.3.jar;C:\Users\lars\.m2\repository\org\jfree\jfreechart\1.5.5\jfreechart-1.5.5.jar;C:\Users\lars\.m2\repository\org\jfree\org.jfree
.pdf\2.0.1\org.jfree.pdf-2.0.1.jar;C:\Users\lars\.m2\repository\org\jfree\jcommon\1.0.24\jcommon-1.0.24.jar;C:\Users\lars\.m2\repository\org\postgresql\postgresql\42.7.3\postgresql-42.7.3.jar;C:\Users\lars\.m2\repository\org\checkerframework\checker-qual\3.42.0\checker-qual-3.42.0.jar;C:\Users\lars\.m2\repository\org\springframework\spring-webmvc\6.1.3\spring-webmvc-6.1.3.jar;C:\Users\lars\.m2\repository\org\springframework\spring-aop\6.1.3\spring-aop-6.1.3.jar;C:\Users\lars\.m2\repository\org\s
pringframework\spring-core\6.1.3\spring-core-6.1.3.jar;C:\Users\lars\.m2\repository\org\springframework\spring-jcl\6.1.3\spring-jcl-6.1.3.jar;C:\Users\lars\.m2\repository\org\springframework\spring-expression\6.1.3\spring-expression-6.1.3.jar;C:\Users\lars\.m2\repository\org\springframework\spring-web\6.1.3\spring-web-6.1.3.jar;C:\Users\lars\.m2\repository\io\micrometer\micrometer-observation\1.12.2\micrometer-observation-1.12.2.jar;C:\Users\lars\.m2\repository\io\micrometer\micrometer-commons\1
.12.2\micrometer-commons-1.12.2.jar;C:\Users\lars\.m2\repository\org\springframework\spring-context\6.1.3\spring-context-6.1.3.jar;C:\Users\lars\.m2\repository\org\springframework\spring-context-support\6.1.3\spring-context-support-6.1.3.jar;C:\Users\lars\.m2\repository\org\springframework\spring-beans\6.1.3\spring-beans-6.1.3.jar;C:\Users\lars\.m2\repository\xalan\xalan\2.7.3\xalan-2.7.3.jar;C:\Users\lars\.m2\repository\xalan\serializer\2.7.3\serializer-2.7.3.jar;C:\Users\lars\.m2\repository\ja
karta\servlet\jakarta.servlet-api\6.0.0\jakarta.servlet-api-6.0.0.jar;C:\Users\lars\.m2\repository\jakarta\servlet\jsp\jakarta.servlet.jsp-api\3.1.0\jakarta.servlet.jsp-api-3.1.0.jar;C:\Users\lars\.m2\repository\org\glassfish\web\jakarta.servlet.jsp\3.0.0\jakarta.servlet.jsp-3.0.0.jar;C:\Users\lars\.m2\repository\jakarta\el\jakarta.el-api\4.0.0\jakarta.el-api-4.0.0.jar;C:\Users\lars\.m2\repository\org\apache\ant\ant\1.10.8\ant-1.10.8.jar;C:\Users\lars\.m2\repository\org\apache\ant\ant-launcher\1
.10.8\ant-launcher-1.10.8.jar;C:\Users\lars\.m2\repository\eclipse\jdtcore\3.1.0\jdtcore-3.1.0.jar;C:\Users\lars\.m2\repository\jakarta\servlet\jsp\jstl\jakarta.servlet.jsp.jstl-api\3.0.2\jakarta.servlet.jsp.jstl-api-3.0.2.jar;C:\Users\lars\.m2\repository\org\glassfish\web\jakarta.servlet.jsp.jstl\3.0.1\jakarta.servlet.jsp.jstl-3.0.1.jar;C:\Users\lars\.m2\repository\org\apache\commons\commons-rng-simple\1.6\commons-rng-simple-1.6.jar;C:\Users\lars\.m2\repository\org\apache\commons\commons-rng-cl
ient-api\1.6\commons-rng-client-api-1.6.jar;C:\Users\lars\.m2\repository\org\apache\commons\commons-rng-core\1.6\commons-rng-core-1.6.jar;C:\Users\lars\.m2\repository\commons-io\commons-io\2.15.1\commons-io-2.15.1.jar;C:\Users\lars\.m2\repository\org\apache\commons\commons-dbcp2\2.12.0\commons-dbcp2-2.12.0.jar;C:\Users\lars\.m2\repository\org\apache\commons\commons-pool2\2.12.0\commons-pool2-2.12.0.jar;C:\Users\lars\.m2\repository\jakarta\transaction\jakarta.transaction-api\1.3.3\jakarta.transac
tion-api-1.3.3.jar;C:\Users\lars\.m2\repository\org\apache\commons\commons-lang3\3.15.0\commons-lang3-3.15.0.jar;C:\Users\lars\.m2\repository\org\slf4j\slf4j-api\2.0.13\slf4j-api-2.0.13.jar;C:\Users\lars\.m2\repository\ch\qos\logback\logback-classic\1.4.6\logback-classic-1.4.6.jar;C:\Users\lars\.m2\repository\ch\qos\logback\logback-core\1.4.6\logback-core-1.4.6.jar;C:\Users\lars\.m2\repository\org\springframework\spring-test\6.1.3\spring-test-6.1.3.jar;C:\Users\lars\.m2\repository\junit\junit\4.
13.2\junit-4.13.2.jar;C:\Users\lars\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\lars\bin\eclipse\configuration\org.eclipse.osgi\440\0\.cp;C:\Users\lars\bin\eclipse\configuration\org.eclipse.osgi\439\0\.cp
	 * 
	 * 
	 */
}
