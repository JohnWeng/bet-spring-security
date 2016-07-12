//package demo;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.UUID;
//
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800)  // will look for the RedisConnectionFactory that Boot provides
//@SpringBootApplication
//@RestController
//public class DemoApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}
////	@Bean
////	HttpSessionStrategy httpSessionStrategy() {
////		return new HeaderHttpSessionStrategy();
////	}
////
////@Primary
////@Bean
//// public RedisTemplate redisTemplateInString2Json() {
////		RedisTemplate redisTemplate=new RedisTemplate();
////		StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
////
////	redisTemplate.setKeySerializer(stringRedisSerializer);
////	redisTemplate.setHashKeySerializer(stringRedisSerializer);
////	redisTemplate.setHashValueSerializer(stringRedisSerializer);
////
////
////		redisTemplate.setConnectionFactory(new JedisConnectionFactory());
////		return redisTemplate;
////
////	}
//
////	@Bean
////	public RedisSerializer springSessionDefaultRedisSerializer() {
////
////	     return new StringRedisSerializer();
////	}
////	springSessionDefaultRedisSerializer
//
////	@Bean(name = {"springSessionDefaultRedisSerializer"})
////	RedisSerializer defaultRedisSerializer(){
////		return  new StringRedisSerializer();
////	}
//
//	@RequestMapping("/")
//	public String hello(HttpSession session) {
//		UUID uid = (UUID) session.getAttribute("uid");
//		if (uid == null) {
//			uid = UUID.randomUUID();
//		}
//		session.setAttribute("uid", uid);
//		session.setAttribute("apple", "pears");
////		session.setMaxInactiveInterval();
////		return uid.toString();
//		return session.getId()+"ddddddddddddd"+session.getMaxInactiveInterval();
//	}
//	@RequestMapping("/2")
//	public String hello2(HttpServletRequest request) {
//		HttpSession session=request.getSession(false);
//
//		if (session==null){
//			request.getSession();
//			return request.getSession().getId();
////			request.getSession();
//		}
//		else
//		{
////			UUID uid = (UUID) session.getAttribute("uid");
//			return session.getId();
//		}
//
//	}
//	@RequestMapping("/3")
//	public String hello3() {
//
//	return "aaa";
//	}
//
//}
