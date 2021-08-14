package com.almightyjava.rest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almightyjava.rest.domain.Employee;
import com.almightyjava.rest.domain.User;
import com.almightyjava.rest.exception.DataException;
import com.almightyjava.rest.repository.EmployeeRepository;
import com.almightyjava.rest.service.EmployeeService;
import com.almightyjava.rest.service.RoleService;
import com.almightyjava.rest.utils.ConstantUtils;
import com.almightyjava.rest.utils.MethodUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(UUID uuid) {
		log.info("EmployeeServiceImpl : findById");
		Optional<Employee> employee = employeeRepository.findById(uuid);
		if (!employee.isPresent()) {
			throw new DataException("Employee UUID is invalid.");
		}
		return employee;
	}

	@Override
	public Employee findByEmployeeId(String employeeId) {
		return employeeRepository.findByEmployeeId(employeeId);
	}

	@Override
	public Employee save(Employee employee) {
		log.info("EmployeeServiceImpl : save");
		employee.setEmployeeUuid(UUID.randomUUID());
		User user = new User(UUID.randomUUID(), employee.getEmailAddress(), MethodUtils.generateRandomPassword(),
				roleService.findByRoleName(ConstantUtils.USER.toString()));
		employee.setUser(user);
		employee.setCreatedOn(new Date());
		employee.setCreatedBy(UUID.randomUUID());
		return employeeRepository.saveAndFlush(employee);
	}

	@Override
	public Employee update(Employee employee) {
		log.info("EmployeeServiceImpl : update");
		Optional<Employee> savedEmployee = employeeRepository.findById(employee.getEmployeeUuid());
		if (savedEmployee.isPresent()) {
			Employee updatedEmployee = savedEmployee.get();
			updatedEmployee.setEmployeeId(employee.getEmployeeId());
			updatedEmployee.setEmailAddress(employee.getEmailAddress());
			updatedEmployee.setFirstName(employee.getFirstName());
			updatedEmployee.setMiddleName(employee.getMiddleName());
			updatedEmployee.setLastName(employee.getLastName());
			updatedEmployee.setLastUpdatedOn(new Date());
			updatedEmployee.setLastUpdatedBy(UUID.randomUUID());
			return employeeRepository.saveAndFlush(updatedEmployee);
		}
		throw new DataException("Employee UUID is invalid");
	}

	@Override
	public String deleteById(UUID uuid) {
		log.info("EmployeeServiceImpl : deleteById");
		Optional<Employee> employee = employeeRepository.findById(uuid);
		if (employee.isPresent()) {
			employeeRepository.delete(employee.get());
			return MethodUtils.convertStringToJSON("message", "Employee Deleted Successfully.");
		}
		throw new DataException("Employee UUID is invalid");
	}

}
