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
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements Serializable {

	@Id
	private String userId=UUID.randomUUID().toString();

	@Column(name = "username")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "delete_state")
	private boolean deleteState;

	@Column(name = "profile_id")
	private String profileId;

	@Column(name = "active")
	private boolean active;

	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "email_verified")
	private boolean emailVerified;

	@Column(name = "mobile_no_verified")
	private boolean mobileNoVerified;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	List<Role> roles=new ArrayList<>();

	@Enumerated( EnumType.STRING)
	@Column(name = "user_status")
	private UserState userStatus;
}
