package org.saltframework.core.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jmock.lib.concurrent.Blitzer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 3.
 */
public class FileSystemSupportTest {
	AtomicInteger c;
	Blitzer blitzer = new Blitzer(20, 5);

	@Before
	public void setUp() {
		c = new AtomicInteger(0);
	}

	@After
	public void tearDown() throws InterruptedException {
		blitzer.shutdown();
	}

	void task() throws IOException {
		FileSystemSupport fileSystemSupport = new FileSystemSupport("/Users/syaku/develop/salt/files");
		fileSystemSupport.setCategory(Category.attachments);
		fileSystemSupport.setSystemCode(SystemCode.modules);
		fileSystemSupport.setSystemName("test");

		Map<String, String> json = new HashMap();
		json.put("good", "good");
		json.put("good1", "good1");
		json.put("kor", "굿굿");

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(json);

		FileSystem fileSystem = fileSystemSupport.save("test.json", jsonInString.getBytes(Charset.defaultCharset()));
		System.out.printf(fileSystem.toString());
	}

	void generalTask(String text) throws IOException {
		FileSystemSupport fileSystemSupport = new FileSystemSupport("/Users/syaku/develop/salt/files");
		fileSystemSupport.setCategory(Category.caches);
		fileSystemSupport.setSystemCode(SystemCode.general);
		fileSystemSupport.setSystemName("test");
		fileSystemSupport.setRename(false);
		fileSystemSupport.setDirDate(false);

		Map<String, String> json = new HashMap();
		json.put("good", "good");
		json.put("good1", "good1");
		json.put("kor", "굿굿");
		json.put("text", text);

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(json);

		FileSystem fileSystem = fileSystemSupport.save("test.json", jsonInString.getBytes(StandardCharsets.UTF_8));
		System.out.printf(fileSystem.toString());
	}

	@Test
	public void save() throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		blitzer.blitz(new Runnable() {
			public void run() {
				c.getAndIncrement();
				System.out.println(Thread.currentThread().getName() + ": done");
				try {
					task();
					generalTask(Thread.currentThread().getName());
				} catch (IOException e) {
					System.out.printf("=====================> " + e.getMessage());
				}

			}
		});// end thread
		sw.stop();
	}

}
