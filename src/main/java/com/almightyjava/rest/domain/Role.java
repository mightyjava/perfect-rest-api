package com.almightyjava.rest.domain;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	@Column(name = "role_uuid")
	private UUID roleUuid;

	@Column(name = "role_name", nullable = false, unique = true, length = 50)
	private String roleName;

	@OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> users;

	@Column(name = "created_on", nullable = false)
	private Date createdOn;

	@Column(name = "created_by", nullable = false)
	private UUID createdBy;

	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;

	@Column(name = "last_updated_by")
	private UUID lastUpdatedBy;

	public Role(String roleName) {
		this.roleName = roleName;
	}
}
