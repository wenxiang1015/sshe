package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.base.dao.BaseDAO;
import com.model.User;
import com.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private BaseDAO<User> userDAO;

	public void save(User user) {
		userDAO.save(user);
	}

	public User login(String username,String password) {
		User u = userDAO.getByHql(" from User u where u.username = ? and u.password = ? ",new Object[]{username,DigestUtils.md5DigestAsHex(password.getBytes())});
		return u;
	}

}
