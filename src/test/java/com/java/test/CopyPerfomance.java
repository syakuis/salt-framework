package com.java.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2016. 11. 7.
 */
public class CopyPerfomance {

	@Test
	public void test() {

		Map<String, Object> map = new HashMap<>();
		map.put("a", "a");
		map.put("aa", "a1");
		map.put("aaa", "a2");
		map.put("aaaa", "a3");

		for(int i = 0; i < 10000; i++) {
			Map<String, Object> copy = new HashMap<>(map);
			System.out.println(copy.toString());
		}
	}

	@Test
	public void test2() {

		Map<String, Object> map = new HashMap<>();
		map.put("b", "b");
		map.put("bbb", "b1");
		map.put("bbbbb", "b2");
		map.put("bbbbbb", "b3");

		for(int i = 0; i < 10000; i++) {
			System.out.println(map.toString());
		}
	}
}
