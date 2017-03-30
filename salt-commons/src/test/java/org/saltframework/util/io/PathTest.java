package org.saltframework.util.io;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 30.
 */
public class PathTest {
	@Test
	public void test() throws IOException {
		System.out.println(new ClassPathResource("config.properties").getURI().getPath());
	}
}
