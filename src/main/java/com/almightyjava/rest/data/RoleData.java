package com.almightyjava.rest.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.almightyjava.rest.domain.Role;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
public class RoleData {
	private UUID roleUuid;
	private String roleName;

	public static List<RoleData> parseRoles(List<Role> roles) {
		log.info("RoleData : parseRoles");
		List<RoleData> roleDatas = new ArrayList<>();
		roles.forEach(role -> {
			RoleData roleData = new RoleData();
			BeanUtils.copyProperties(role, roleData);
			roleDatas.add(roleData);
		});
		return roleDatas;
	}

	public static RoleData parseRole(Role role) {
		log.info("RoleData : parseRole");
		RoleData roleData = new RoleData();
		BeanUtils.copyProperties(role, roleData);
		return roleData;
	}

	public static Role parseRoleData(RoleData roleData) {
		log.info("RoleData : parseRoleData");
		Role role = new Role();
		BeanUtils.copyProperties(roleData, role);
		return role;
	}
}
