package com.zfw.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

public class SpringTest {
	@Test
	public void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
		StringRedisTemplate redisTemplate=(StringRedisTemplate) ac.getBean("redisTemplate");
		System.out.println(redisTemplate);
		redisTemplate.opsForValue().set("11", "skyLine");
	}
}