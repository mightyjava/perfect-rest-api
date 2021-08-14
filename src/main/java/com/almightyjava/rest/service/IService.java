package com.almightyjava.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IService<T> {
	List<T> findAll();

	Optional<T> findById(UUID id);

	T save(T t);

	T update(T t);

	String deleteById(UUID id);
}
