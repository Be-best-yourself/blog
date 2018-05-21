package com.zfw.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//首先指定Junit的Runner
@RunWith(SpringJUnit4ClassRunner.class)
// 指明配置文件所在
@ContextConfiguration("classpath:spring-redis.xml")
public class RedisTest {
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	public void testSpringRedis() {
		
		System.out.println(redisTemplate);
		// stringRedisTemplate的操作
		// String读写
		redisTemplate.delete("myStr");
		redisTemplate.opsForValue().set("myStr", "skyLine");
		System.out.println(redisTemplate.opsForValue().get("myStr"));
		System.out.println("---------------");

		// List读写
		redisTemplate.delete("myList");
		redisTemplate.opsForList().rightPush("myList", "T");
		redisTemplate.opsForList().rightPush("myList", "L");
		redisTemplate.opsForList().leftPush("myList", "A");
		List<String> listCache = redisTemplate.opsForList().range("myList", 0, -1);
		for (String s : listCache) {
			System.out.println(s);
		}
		System.out.println("---------------");

		// Set读写
		redisTemplate.delete("mySet");
		redisTemplate.opsForSet().add("mySet", "A");
		redisTemplate.opsForSet().add("mySet", "B");
		redisTemplate.opsForSet().add("mySet", "C");
		Set<String> setCache = redisTemplate.opsForSet().members("mySet");
		for (String s : setCache) {
			System.out.println(s);
		}
		System.out.println("---------------");

		// Hash读写
		redisTemplate.delete("myHash");
		redisTemplate.opsForHash().put("myHash", "BJ", "北京");
		redisTemplate.opsForHash().put("myHash", "SH", "上海");
		redisTemplate.opsForHash().put("myHash", "HN", "河南");
		Map<Object, Object> hashCache = redisTemplate.opsForHash().entries("myHash");
		for (Map.Entry entry : hashCache.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		System.out.println("---------------");
	}
}
