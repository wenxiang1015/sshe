package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.UserService;

public class SpringInitTest {

	@Test
	public void test(){
		Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);  
	    logger.trace("trace level");  
	    logger.debug("debug level");  
	    logger.info("info level");  
	    logger.warn("warn level");  
	    logger.error("error level");  
	    logger.fatal("fatal level");  
	}
	
	@Test
	public void springInit(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		System.out.println(ac);
	}
}
