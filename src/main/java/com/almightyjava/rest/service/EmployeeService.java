package com.almightyjava.rest.service;

import com.almightyjava.rest.domain.Employee;

public interface EmployeeService extends IService<Employee> {

	Employee findByEmployeeId(String employeeId);

}
