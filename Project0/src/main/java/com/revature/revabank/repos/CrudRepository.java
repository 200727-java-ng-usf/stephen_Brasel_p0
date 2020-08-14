package com.revature.revabank.repos;

import java.util.Set;

public interface CrudRepository<T> {
	public abstract T save(T t);
	public abstract Set<T> findAll();
	public abstract T findById(String id);
	public abstract boolean update(T t);
	public abstract boolean deleteById(String id);
}
