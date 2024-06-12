package com.jsfund.gmall2019.manage;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootTest
public class GmallManagerServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void testMap(){
		HashMap<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1,"张三");
		hashMap.put(2,"李四");
		Iterator<Map.Entry<Integer, String>> iterator = hashMap.entrySet().iterator();
		while (iterator.hasNext()){
			Map.Entry<Integer, String> next = iterator.next();
			Integer key = next.getKey();
			String value = next.getValue();
			System.out.println("key:"+key);
			System.out.println("value:"+value);
		}

	}
	@Test
	public void testMap2() {
		Map<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1, "张三");
		hashMap.put(2, "李四");
		Iterator<String> iterator = hashMap.values().iterator();
		while (iterator.hasNext()){
			String next = iterator.next();
			System.out.println(next);
		}

	}@Test
	public void testMap3() {
		Map<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1, "张三");
		hashMap.put(2, "李四");
		Iterator<Integer> iterator = hashMap.keySet().iterator();
		while (iterator.hasNext()){
			Integer next = iterator.next();
			System.out.println(next);
			String value = hashMap.get(next);
			System.out.println(value);

		}


	}
}
