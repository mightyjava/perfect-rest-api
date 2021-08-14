package com.almightyjava.rest.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IQueryResource<T> {
	@GetMapping
	ResponseEntity<List<T>> findAll();

	@GetMapping("{id}")
	ResponseEntity<T> findById(@PathVariable UUID id);
}
