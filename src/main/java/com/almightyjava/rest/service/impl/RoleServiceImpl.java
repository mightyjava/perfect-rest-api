package com.almightyjava.rest.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almightyjava.rest.domain.Role;
import com.almightyjava.rest.exception.DataException;
import com.almightyjava.rest.repository.RoleRepository;
import com.almightyjava.rest.service.RoleService;
import com.almightyjava.rest.utils.MethodUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(UUID uuid) {
		log.info("RoleServiceImpl : findById");
		Optional<Role> role = roleRepository.findById(uuid);
		if (!role.isPresent()) {
			throw new DataException("Role UUID is invalid.");
		}
		return role;
	}

	@Override
	public Role findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.saveAndFlush(role);
	}

	@Override
	public Role update(Role role) {
		return roleRepository.saveAndFlush(role);
	}

	@Override
	public String deleteById(UUID uuid) {
		log.info("RoleServiceImpl : deleteById");
		Optional<Role> role = roleRepository.findById(uuid);
		if (role.isPresent()) {
			roleRepository.delete(role.get());
			return MethodUtils.convertStringToJSON("message", "Role Deleted Successfully.");
		}
		throw new DataException("Role UUID is invalid.");
	}

}
