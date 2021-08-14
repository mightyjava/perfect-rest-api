package com.almightyjava.rest.service;

import com.almightyjava.rest.domain.Role;

public interface RoleService extends IService<Role> {
	Role findByRoleName(String roleName);
}
