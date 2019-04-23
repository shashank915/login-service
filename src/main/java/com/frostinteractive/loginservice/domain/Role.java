package com.frostinteractive.loginservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

	@Id
	private String roleId= UUID.randomUUID().toString();

	@Column(name = "rolename")
	private String roleName;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_policy", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "policy_id") })
	public List<Policy> policies=new ArrayList<>();

}
