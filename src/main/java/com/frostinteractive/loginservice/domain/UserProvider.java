//package com.frostinteractive.loginservice.domain;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class UserProvider {
//	private static List<AppUser> appusers = new ArrayList();
//	private static List<Role> roles = new ArrayList();
//	private static List<Policy> policies = new ArrayList();
//
//	static {
//		Policy policy1 = new Policy();
//		policy1.setPolicy("create_subject");
//		policy1.setRestUrl("/subjects");
//		policy1.setHttpMethod(HttpMethod.POST);
//
//		Policy policy2 = new Policy();
//		policy2.setPolicy("get_subject");
//		policy2.setRestUrl("/subjects/*");
//		policy2.setHttpMethod(HttpMethod.GET);
//
//		Policy policy3 = new Policy();
//		policy3.setPolicy("update_subject");
//		policy3.setRestUrl("/subjects");
//		policy3.setHttpMethod(HttpMethod.PUT);
//
//		Policy policy4 = new Policy();
//		policy4.setPolicy("delete_subject");
//		policy4.setRestUrl("/subjects/*");
//		policy4.setHttpMethod(HttpMethod.DELETE);
//
//		policies.add(policy1);
//		policies.add(policy2);
//		policies.add(policy3);
//		policies.add(policy4);
//
//		Role role1 = new Role();
//		role1.setRoleName("CONTENT_DEVELOPER");
//		role1.setPolicies(Arrays.asList(policy2,policy3));
//
//		Role role2 = new Role();
//		role2.setRoleName("CONTENT_ADMIN");
//		role2.setPolicies(Arrays.asList(policy1,policy2,policy3));
//
//		Role role3 = new Role();
//		role3.setRoleName("SUPER_ADMIN");
//		role3.setPolicies(Arrays.asList(policy1,policy2,policy3,policy4));
//		roles.add(role1);
//		roles.add(role2);
//		roles.add(role3);
//
//
//		AppUser appUser1 = new AppUser();
//		appUser1.setUserName("CD");
//		appUser1.setPassword(new BCryptPasswordEncoder().encode("CDPassword"));
//		appUser1.setRoles(Arrays.asList(role1));
//
//		AppUser appUser2 = new AppUser();
//		appUser2.setUserName("CA");
//		appUser2.setPassword(new BCryptPasswordEncoder().encode("CAPassword"));
//		appUser2.setRoles(Arrays.asList(role2));
//
//		AppUser appUser3 = new AppUser();
//		appUser3.setUserName("SA");
//		appUser3.setPassword(new BCryptPasswordEncoder().encode("SAPassword"));
//		appUser3.setRoles(Arrays.asList(role3));
//
//		appusers.add(appUser1);
//		appusers.add(appUser2);
//		appusers.add(appUser3);
//	}
//
//	public static List<AppUser> provideUsers(){
//		return appusers;
//	}
//
//	public static List<Role> getRoles(){
//		return roles;
//	}
//
//	public static List<Policy> getPolicies() {
//		return policies;
//	}
//}
