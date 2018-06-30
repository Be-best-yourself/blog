package com.zfw.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.Jedis;

public class SpringTest {
	@Test
	public void test1() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
		StringRedisTemplate redisTemplate=(StringRedisTemplate) ac.getBean("redisTemplate");
		System.out.println(redisTemplate);
		redisTemplate.opsForValue().set("11", "skyLine");
	}
	@Test
	public void test2(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-redis.xml,classpath:spring-mvc.xml");
		JedisConnectionFactory connectionFactory = (JedisConnectionFactory) ac.getBean("connectionFactory");
		Jedis jedis = (Jedis) connectionFactory.getConnection().getNativeConnection();
		 boolean keyExist = jedis.exists("aaa");
		    // NX是不存在时才set， XX是存在时才set， EX是秒，PX是毫秒
		    if (keyExist) {
		    	jedis.del("aaa");
		    }
		    jedis.set("aaa", "1234567", "NX", "EX", 60);
		
	}
}