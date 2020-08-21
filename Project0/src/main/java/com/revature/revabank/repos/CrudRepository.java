package com.revature.revabank.repos;

import java.util.Optional;
import java.util.Set;

public interface CrudRepository<T> {
	public abstract Optional<T> save(T t);
	public abstract Set<T> findAll();
	public abstract T findById(String id);
	public abstract boolean update(T t);
	public abstract boolean deleteById(String id);
}
