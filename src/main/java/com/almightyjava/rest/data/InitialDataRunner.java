package com.almightyjava.rest.data;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.almightyjava.rest.domain.Employee;
import com.almightyjava.rest.domain.Role;
import com.almightyjava.rest.domain.User;
import com.almightyjava.rest.repository.EmployeeRepository;
import com.almightyjava.rest.service.RoleService;
import com.almightyjava.rest.utils.ConstantUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitialDataRunner implements CommandLineRunner {

	@Autowired
	private RoleService roleService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		log.info("InitialDataRunner : run");
		UUID randomRoleId = UUID.randomUUID();
		Role role = new Role(ConstantUtils.ADMIN.toString());
		role.setRoleUuid(randomRoleId);
		role.setCreatedOn(new Date());
		role.setCreatedBy(randomRoleId);
		Role roleObject = roleService.save(role);
		log.info("Role - " + roleObject.getRoleName() + " saved successfully.");

		randomRoleId = UUID.randomUUID();
		role = new Role(ConstantUtils.USER.toString());
		role.setRoleUuid(randomRoleId);
		role.setCreatedOn(new Date());
		role.setCreatedBy(randomRoleId);
		roleObject = roleService.save(role);
		log.info("Role - " + roleObject.getRoleName() + " saved successfully.");

		String emailAddress = "admin@almightyjava.com";
		User user = new User(UUID.randomUUID(), emailAddress, new BCryptPasswordEncoder().encode("123456"),
				roleService.findByRoleName(ConstantUtils.ADMIN.toString()));

		UUID randomEmployeeId = UUID.randomUUID();
		Employee employee = new Employee(randomEmployeeId, "AJ00001", emailAddress, "system", null, "admin", new Date(),
				randomEmployeeId, null, null, user);
		Employee employeeObject = employeeRepository.saveAndFlush(employee);
		log.info("Employee - " + employeeObject.getEmailAddress() + " saved successfully.");
	}
}
