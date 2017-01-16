package com.action;

import com.service.InitService;
import com.model.Menu;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.action.BaseAction;
import com.base.util.Json;

/**
 * 初始化数据库使用
 * 
 * @author 
 * 
 */
@Action(value="initAction")
public class InitAction extends BaseAction<Menu> {

	@Autowired
	private InitService service;

	synchronized public void initDb() {
		Json j = new Json();
		service.initDb();
		j.setSuccess(true);
		j.setMsg("修复成功！");
		writeJson(j);
	}

}
