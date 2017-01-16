package com;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.model.User;
import com.service.UserService;

public class SessionTest {
	
	@Test
	public void sessionTest(){
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-hibernate.xml"});
		UserService userService = (UserService) ac.getBean("userServiceImpl");
		User user = new User();
		user.setAge(11);
		user.setUsername("xxx");
		user.setGender(1);
		userService.save(user);
	}
}
