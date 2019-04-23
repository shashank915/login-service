package com.frostinteractive.loginservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "policy")
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
	@Id
	private String policyId= UUID.randomUUID().toString();

	@Column(name = "policy")
	private String policy;

	@OneToOne
	@JoinColumn(name = "uri_id")
	private RestUri restUri;
}
