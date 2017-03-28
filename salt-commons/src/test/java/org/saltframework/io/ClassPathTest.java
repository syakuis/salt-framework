package org.saltframework.io;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * classpath 내에 특정 파일 검색
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 3. 29.
 */
public class ClassPathTest {
	@Test
	public void test() throws IOException {
		System.out.println(new ClassPathResource("config.properties").getURI().getPath());
	}
}
