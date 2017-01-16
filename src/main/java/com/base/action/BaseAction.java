package com.base.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage(value="default")
@Namespace(value="/")
@Action
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected T model;
	
	public BaseAction() {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class) parameterizedType.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public T getModel() {
		return model;
	}
	
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(Object object) {
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().getWriter().write(JSON.toJSONString(object));
			getResponse().getWriter().flush();
			getResponse().getWriter().close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
