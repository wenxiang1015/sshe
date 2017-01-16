package com.base.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDAOImpl<T> implements BaseDAO<T> {
	
	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public Serializable save(T o) {
		if (o != null) {
			return getCurrentSession().save(o);
		}
		return null;
	}

	public void delete(T o) {
		if (o != null) {
			getCurrentSession().delete(o);
		}
	}

	public void update(T o) {
		if (o != null) {
			getCurrentSession().update(o);
		}
	}

	public void saveOrUpdate(T o) {
		if (o != null) {
			getCurrentSession().saveOrUpdate(o);
		}
	}

	public T getById(Class<T> c, Serializable id) {
		return (T) getCurrentSession().get(c, id);
	}

	public T getByHql(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		List<T> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public T getByHql(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	public List<T> find(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		return q.getResultList();
	}

	public List<T> find(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return q.getResultList();
	}

	public List<T> find(String hql, int page, int rows) {
		Query q = getCurrentSession().createQuery(hql);
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
	}

	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).getResultList();
	}

	public Long count(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		return (Long) q.getSingleResult();
	}

	public Long count(String hql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.getSingleResult();
	}

	public int executeHql(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int executeHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Map> findBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map> findBySql(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map> findBySql(String sql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Map> findBySql(String sql, Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	public int executeSql(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int executeSql(String sql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	public BigInteger countBySql(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public BigInteger countBySql(String sql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}


	public T getByHql(String hql, Object[] params) {
		Query q = getCurrentSession().createQuery(hql);
		if(params!=null&&params.length>0){
			for(int i=0;i<params.length;i++){
				q.setParameter(i, params[i]);
			}
		}
		List<T> list = q.getResultList();
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
