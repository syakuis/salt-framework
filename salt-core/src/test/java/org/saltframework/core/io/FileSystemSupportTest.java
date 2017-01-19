package org.saltframework.core.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jmock.lib.concurrent.Blitzer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.saltframework.core.io.enums.Category;
import org.saltframework.core.io.enums.SystemCode;
import org.springframework.util.StopWatch;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. ThreadSafe 테스트
 * 2. FileSystemSupport 파일 생성 테스트
 * 3. FileWrite 를 이용한 테스트
 * 4. File 생성 테스트
 *
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http ://syaku.tistory.com
 * @since 2017. 1. 3.
 */
public class FileSystemSupportTest {
	/**
	 * The C.
	 */
// thread safe test
	AtomicInteger c;
	/**
	 * The Blitzer.
	 */
	Blitzer blitzer = new Blitzer(20, 5);

	/**
	 * The Absolute path.
	 */
	String absolutePath = "/Users/syaku/develop/salt/files";

	/**
	 * Sets up.
	 */
	@Before
	public void setUp() {
		c = new AtomicInteger(0);
	}

	/**
	 * Tear down.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	@After
	public void tearDown() throws InterruptedException {
		blitzer.shutdown();
	}

	/**
	 * 새로운 파일을 생성한다.
	 *
	 * @throws IOException the io exception
	 */
	void modulesFileSystem() throws IOException {
		FileSystemSupport fileSystemSupport = new FileSystemSupport(absolutePath);
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

	/**
	 * 새로운 파일을 생성한다. 기존에 있으면 덮어쓴다.
	 *
	 * @param text the text
	 * @throws IOException the io exception
	 */
	void generalFileSystem(String text) throws IOException {
		FileSystemSupport fileSystemSupport = new FileSystemSupport(absolutePath);
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

	/**
	 * FileSystem ThreadSafe 테스트
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void fileSystemThreadSafe() throws Exception {
		StopWatch sw = new StopWatch();
		sw.start();
		blitzer.blitz(new Runnable() {
			public void run() {
				c.getAndIncrement();
				System.out.println(Thread.currentThread().getName() + ": done");
				try {
					modulesFileSystem();
					generalFileSystem(Thread.currentThread().getName());
				} catch (IOException e) {
					System.out.printf("=====================> " + e.getMessage());
				}

			}
		});// end thread
		sw.stop();
	}

	/**
	 * FileWrite 를 이용한 기존 파일 내용 추가하기.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void fileWrite() throws Exception {
		FileSystemSupport fileSystemSupport = new FileSystemSupport(absolutePath);
		fileSystemSupport.setCategory(Category.caches);
		fileSystemSupport.setSystemCode(SystemCode.general);
		fileSystemSupport.setSystemName("test");
		fileSystemSupport.setRename(false);
		fileSystemSupport.setDirDate(false);

		FileSystem fileSystem = fileSystemSupport.initiation("test2.json");
		for(int i = 0; i < 100; i++) {
			FileWriter fileWriter = new FileWriter(fileSystem.getFile(), true);
			fileWriter.write("good\n");
			fileWriter.close();
		}
	}

}
