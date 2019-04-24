package com.frostinteractive.loginservice.repository;

import com.frostinteractive.loginservice.domain.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AppUserRepository extends CrudRepository<AppUser, String> {
	
	Optional<AppUser> findByUserName(String userName);
	Optional<AppUser> findByEmailOrMobile(String mobile, String email);
}
