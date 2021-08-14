package com.almightyjava.rest.resource.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almightyjava.rest.data.EmployeeData;
import com.almightyjava.rest.resource.IMutationResource;
import com.almightyjava.rest.resource.IQueryResource;
import com.almightyjava.rest.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeResourceImpl implements IQueryResource<EmployeeData>, IMutationResource<EmployeeData> {

	@Autowired
	private EmployeeService employeeService;

	@Override
	public ResponseEntity<List<EmployeeData>> findAll() {
		return ResponseEntity.ok(EmployeeData.parseEmployees(employeeService.findAll()));
	}

	@Override
	public ResponseEntity<EmployeeData> findById(UUID id) {
		return ResponseEntity.ok(EmployeeData.parseEmployee(employeeService.findById(id).get()));
	}

	@Override
	public ResponseEntity<EmployeeData> save(EmployeeData employeeData) {
		return ResponseEntity
				.ok(EmployeeData.parseEmployee(employeeService.save(EmployeeData.parseEmployeeData(employeeData))));
	}

	@Override
	public ResponseEntity<EmployeeData> update(EmployeeData employeeData) {
		return ResponseEntity
				.ok(EmployeeData.parseEmployee(employeeService.update(EmployeeData.parseEmployeeData(employeeData))));
	}

	@Override
	public ResponseEntity<String> deleteById(UUID id) {
		return ResponseEntity.ok(employeeService.deleteById(id).toString());
	}

}
