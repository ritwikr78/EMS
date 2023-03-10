package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "positions")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Positions extends BaseEntity {
	@Column(name = "position_name")
	public String positionName;
	@Enumerated(EnumType.STRING)
	public Roles role;
}
