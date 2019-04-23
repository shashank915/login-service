package com.frostinteractive.loginservice.repository;

import com.frostinteractive.loginservice.domain.Policy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpMethod;

import java.util.Optional;

public interface PolicyRepository extends CrudRepository<Policy,String> {

//    Optional<Policy> findByRestUrlAndHttpMethod(String restUrl, HttpMethod httpMethod);
}
