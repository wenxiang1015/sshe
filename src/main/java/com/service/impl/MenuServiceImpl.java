package com.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.BaseDAO;
import com.model.Menu;
import com.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Resource
	private BaseDAO<Menu> menuDAO;
}
