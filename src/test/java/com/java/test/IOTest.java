package com.java.test;

import org.jmock.lib.concurrent.Blitzer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 1. 6.
 */
public class IOTest {
	String PATH = "/Users/syaku/develop/salt/files";
	int count = 0;

	// thread safe test
	AtomicInteger c;
	Blitzer blitzer = new Blitzer(1000, 10);

	@Before
	public void setUp() {
		c = new AtomicInteger(0);
	}

	@After
	public void tearDown() throws InterruptedException {
		blitzer.shutdown();
	}

	private void write(String text, int idx) throws IOException {
		OutputStream fileOutputStream;

		byte[] bytes = new String(idx + ")굿이다." + text + "\n").getBytes();

		fileOutputStream = new FileOutputStream(PATH + "/memo.txt", true);
		fileOutputStream.write(bytes);
		fileOutputStream.flush();
		fileOutputStream.close();
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
					write(Thread.currentThread().getName(), count++);
				} catch (IOException e) {
					System.out.printf("=====================> " + e.getMessage());
				}

			}
		});// end thread
		sw.stop();
	}
}
