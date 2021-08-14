package com.almightyjava.rest.resource.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.almightyjava.rest.data.RoleData;
import com.almightyjava.rest.domain.Role;
import com.almightyjava.rest.resource.IMutationResource;
import com.almightyjava.rest.resource.IQueryResource;
import com.almightyjava.rest.service.IService;

@RequestMapping("/role")
public class RoleResourceImpl implements IQueryResource<RoleData>, IMutationResource<RoleData> {

	@Autowired
	private IService<Role> roleService;

	@Override
	public ResponseEntity<List<RoleData>> findAll() {
		return ResponseEntity.ok(RoleData.parseRoles(roleService.findAll()));
	}

	@Override
	public ResponseEntity<RoleData> findById(UUID id) {
		return ResponseEntity.ok(RoleData.parseRole(roleService.findById(id).get()));
	}

	@Override
	public ResponseEntity<RoleData> save(RoleData roleData) {
		return ResponseEntity.ok(RoleData.parseRole(roleService.save(RoleData.parseRoleData(roleData))));
	}

	@Override
	public ResponseEntity<RoleData> update(RoleData roleData) {
		return ResponseEntity.ok(RoleData.parseRole(roleService.update(RoleData.parseRoleData(roleData))));
	}

	@Override
	public ResponseEntity<String> deleteById(UUID id) {
		return ResponseEntity.ok(roleService.deleteById(id).toString());
	}

}
