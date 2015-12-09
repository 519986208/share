package com.cly.ssi.dao;

import java.io.Serializable;

/**
 * 通用的dao层，子类可自定义需要的接口
 */
public interface BaseDao<T> {

	public void save(T t);

	public T selectById(Serializable id);
}
