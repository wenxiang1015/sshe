package com.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.base.dao.BaseDAO;
import com.model.Menu;
import com.model.User;
import com.service.InitService;

@Service
public class InitServiceImpl implements InitService {

	private static final String FILEPATH = "initDataBase.xml";

	@Autowired
	private BaseDAO baseDao;

	synchronized public void initDb() {
		try {
			Document document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILEPATH));
			
			initMenu(document);//初始化菜单
			
			initUser(document);//初始化用户
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void initMenu(Document document) {
		List childNodes = document.selectNodes("//menus/menu");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			Menu menu = (Menu) baseDao.getById(Menu.class, node.valueOf("@id"));
			if (menu == null) {
				menu = new Menu();
			}
			menu.setId(node.valueOf("@id"));
			menu.setMenuName(node.valueOf("@menuName"));
			menu.setUrl(node.valueOf("@url"));
			menu.setIcon(node.valueOf("@icon"));

			if (!StringUtils.isBlank(node.valueOf("@pid"))) {
				menu.setMenu((Menu)baseDao.getById(Menu.class, node.valueOf("@pid")));
			}else{
				menu.setMenu(null);
			}

			baseDao.saveOrUpdate(menu);
		}
	}
	
	private void initUser(Document document){
		List childNodes = document.selectNodes("//users/user");
		for (Object obj : childNodes) {
			Node node = (Node) obj;
			User user = (User) baseDao.getById(User.class, node.valueOf("@id"));
			if (user == null) {
				user = new User();
			}
			user.setId(node.valueOf("@id"));
			user.setUsername(node.valueOf("@username"));
			user.setPassword(DigestUtils.md5DigestAsHex(node.valueOf("@password").getBytes()));
			user.setAge(Integer.valueOf(node.valueOf("@age")));
			List<User> ul = baseDao.find("from User u where u.username = '" + user.getUsername() + "' and u.id != '" + user.getId() + "'");
			for (User u : ul) {
				baseDao.delete(u);
			}
			baseDao.saveOrUpdate(user);
		}
	}
}
