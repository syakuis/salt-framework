package org.saltframework.core.io;

import org.junit.Test;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 3.
 */
public class FileSystemTest {

	@Test
	public void createDateFormatPath() {
		FileSystemSupport fileSystem = new FileSystemSupport();

		Assert.notNull(fileSystem.getDateFormatPath());

		System.out.printf(UUID.randomUUID().toString());
	}
}
