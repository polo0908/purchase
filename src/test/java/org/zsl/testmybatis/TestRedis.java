package org.zsl.testmybatis;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.cn.hnust.util.ApplicationContextUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestRedis {
 
// @Autowired
// private RedisTemplate<String, Object> redisTemplate;
//	@Autowired
//	private RedisUtil redis;

 @Test
 public void testRedisObj() {
//	 Map<String, Object> properties = new HashMap<>();
//	 properties.put("123", "hello");
//	 properties.put("abc", 456);
//	  
//	 redisTemplate.opsForHash().putAll("hash", properties);
//	  
//	 Map<Object, Object> ans = redisTemplate.opsForHash().entries("hash");
	 System.out.println("ans: " + 1);
	 
//	 RedisUtil redis = new RedisUtil();
//	 redis.set("test", "1232");
	 
 }

}
