package com.frostinteractive.loginservice.service;

import com.frostinteractive.loginservice.domain.AppUser;
import com.frostinteractive.loginservice.domain.UserPrincipal;
import com.frostinteractive.loginservice.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String emailOrMobileNo) throws UsernameNotFoundException {

		//UserProvider is a custom class that provides some users with hard-coded roles and policies
//		List<AppUser> appUserList = UserProvider.provideUsers();

		final Optional<AppUser> appUserOptional =
				appUserRepository.findByEmailOrMobile(emailOrMobileNo,emailOrMobileNo);
//				appUserList.stream().filter(appUser-> appUser.getUsername().equals(username)).findAny();

		if(!appUserOptional.isPresent()) {
			// If user not found. Throw this exception.
			log.error("---------------------------------User Not Found----------------------------");
			throw new UsernameNotFoundException("User with email/mobile(" + emailOrMobileNo + ") not found");
		}

		return UserPrincipal.create(appUserOptional.get(), emailOrMobileNo);
	}
}
