package com.frostinteractive.loginservice.service;

import com.frostinteractive.loginservice.domain.AppUser;
import com.frostinteractive.loginservice.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//UserProvider is a custom class that provides some users with hard-coded roles and policies
//		List<AppUser> appUserList = UserProvider.provideUsers();

		final Optional<AppUser> appUserOptional =
				appUserRepository.findByUserName(username);
//				appUserList.stream().filter(appUser-> appUser.getUsername().equals(username)).findAny();
		
		if(!appUserOptional.isPresent()) {
			// If user not found. Throw this exception.
			log.error("---------------------------------User Not Found----------------------------");
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
		
//		AppUser appUser = appUserOptional.get();
//		System.out.println("Id :: " + appUser.getId());
//		System.out.println("Username :: " + appUser.getUsername());
//		System.out.println("Password :: " + appUser.getPassword());
//		System.out.print("Role :: ");
//		appUser.getRoles().forEach(role-> System.out.print(role.getRole()+" "));


		// Remember that Spring needs roles to be in this format: "ROLE_" + userRole
		// (i.e. "ROLE_ADMIN")
		// So, we need to set it to that format, so we can verify and compare roles
		// (i.e. hasRole("ADMIN")).
		AppUser appUser = appUserOptional.get();
		List<String> roles = appUser.getRoles().stream().map(role-> "ROLE_" + role.getRoleName()).collect(Collectors.toList());
		List<String> policies = appUser.getRoles().stream().flatMap(role->role.getPolicies().stream())
				.map(policy->policy.getPolicy()).collect(Collectors.toList());

		String[] roleArray = new String[roles.size()];
		roleArray = roles.toArray(roleArray);
		String[] policyArray = new String[policies.size()];
		policyArray = policies.toArray(policyArray);
		
//		List<String> roles = appUser.getRoles().stream().map(role-> "ROLE_" + role.getRole()).collect(Collectors.toList());
//		String[] rolesArray = new String[roles.size()];
//		rolesArray = roles.toArray(rolesArray);
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.addAll(AuthorityUtils.createAuthorityList(roleArray));
		grantedAuthorities.addAll(AuthorityUtils.createAuthorityList(policyArray));
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(rolesArray);

		// The "User" class is provided by Spring and represents a model class for user
		// to be returned by UserDetailsService
		// And used by auth manager to verify and check user authentication.
		return new User(appUser.getUserName(), appUser.getPassword(), grantedAuthorities);
		}
}
