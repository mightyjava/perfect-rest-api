package com.almightyjava.rest.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue
	private UUID employeeUuid;

	@Column(name = "employee_id", nullable = false, unique = true)
	private String employeeId;

	@Column(name = "email_address", nullable = false, unique = true)
	private String emailAddress;

	@Column(name = "first_name", nullable = false, unique = true)
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "created_on", nullable = false)
	private Date createdOn;

	@Column(name = "created_by", nullable = false)
	private UUID createdBy;

	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;

	@Column(name = "last_updated_by")
	private UUID lastUpdatedBy;

	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

}
