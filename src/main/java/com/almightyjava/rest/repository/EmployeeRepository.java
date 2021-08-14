package com.almightyjava.rest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.almightyjava.rest.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	Employee findByEmployeeId(String employeeId);

	Employee findByEmailAddress(String emailAddress);

}
