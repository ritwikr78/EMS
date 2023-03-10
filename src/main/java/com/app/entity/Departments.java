package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Departments extends BaseEntity {
	@NotNull
	@Column(name = "department_name")
	private String departmentName;
}
