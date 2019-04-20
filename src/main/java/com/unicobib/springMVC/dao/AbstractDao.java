package com.unicobib.springMVC.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistance;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistance = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistance,key);
	}
	
	public void persist(T entity) {
		getSession().persist(entity);
	}
	
	public void delete(T entity) {
		getSession().delete(entity);
	}
	protected Criteria createEntity() {
		return getSession().createCriteria(persistance);
	}
}
