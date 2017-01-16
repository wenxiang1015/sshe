package com.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;

import com.base.action.BaseAction;
import com.base.util.Json;
import com.model.User;
import com.service.UserService;

/*
 *  action 注解也可以写在方法上
 *    相当于  	<action name="" class="" method=""></action>
 */
@Action(value="userAction")
public class UserAction extends BaseAction<User> {
	
	@Resource
	private UserService userService;
	
	public void register(){
//		String username = ServletActionContext.getRequest().getParameter("username");
		User user = new User();
		//第三个参数为要忽略的属性
		BeanUtils.copyProperties(model, user, new String[]{"password"});
		user.setPassword(DigestUtils.md5DigestAsHex(model.getPassword().getBytes()));
		user.setCreateTime(new Date());
		
		Json json = new Json();
		try {
			//也可以直接 save model 注意密码要加密
			userService.save(user);
			json.setSuccess(true);
			json.setMsg("注册成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		
		writeJson(json);
	}
	
	public void login(){
		User u = userService.login(model.getUsername(),model.getPassword());
		
		Json json = new Json();
		if(u!=null){
			json.setSuccess(true);
			json.setMsg("登录成功");
		}else{
			json.setMsg("账户或密码错误");
		}
		
		writeJson(json);
	}
	
//	public void test(){
//		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
//		UserService userService = (UserService) ac.getBean("userServiceImpl");
//	}
}
