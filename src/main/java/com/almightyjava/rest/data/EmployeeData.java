package com.almightyjava.rest.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.almightyjava.rest.domain.Employee;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
public class EmployeeData {
	private UUID employeeUuid;
	private String employeeId;
	private String emailAddress;
	private String firstName;
	private String middleName;
	private String lastName;

	public static List<EmployeeData> parseEmployees(List<Employee> employees) {
		log.info("EmployeeData : parseEmployees");
		List<EmployeeData> employeeDatas = new ArrayList<>();
		if (!employees.isEmpty()) {
			employees.forEach(employee -> {
				EmployeeData employeeData = new EmployeeData();
				BeanUtils.copyProperties(employee, employeeData);
				employeeDatas.add(employeeData);
			});
		}
		return employeeDatas;
	}

	public static EmployeeData parseEmployee(Employee employee) {
		log.info("EmployeeData : parseEmployee");
		EmployeeData employeeData = new EmployeeData();
		BeanUtils.copyProperties(employee, employeeData);
		return employeeData;
	}

	public static Employee parseEmployeeData(EmployeeData employeeData) {
		log.info("EmployeeData : parseEmployeeData");
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeData, employee);
		UUID employeeUuid = employeeData.getEmployeeUuid();
		if (employeeUuid != null) {
			employee.setEmployeeUuid(employeeUuid);
		}
		return employee;
	}

}
