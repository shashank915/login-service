package com.frostinteractive.loginservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Data
public class UserPrincipal implements UserDetails {

    private String id;
    private String name;
    private String email;
    private String mobileNo;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();

    private UserPrincipal(String id, String name, String email, String mobileNo, String password, Collection<? extends GrantedAuthority> authorities, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public static UserPrincipal create(AppUser appUser, String emailOrMobileNo){
        List<String> roles = appUser.getRoles().stream().map(role-> "ROLE_" + role.getRoleName()).collect(Collectors.toList());
        List<String> policies = appUser.getRoles().stream().flatMap(role->role.getPolicies().stream())
                .map(policy->policy.getPolicy()).collect(Collectors.toList());

        String[] roleArray = new String[roles.size()];
        roleArray = roles.toArray(roleArray);
        String[] policyArray = new String[policies.size()];

        policyArray = policies.toArray(policyArray);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.addAll(AuthorityUtils.createAuthorityList(roleArray));
        grantedAuthorities.addAll(AuthorityUtils.createAuthorityList(policyArray));

        boolean userActive = false;
        //checking for User status for CHANGE_PASSWORD status
        if(appUser.getUserStatus() == UserState.CHANGE_PASSWORD){
            throw new RuntimeException("Please change the password before the first login");
        }


        if(appUser.isActive() && appUser.getUserStatus() == UserState.COMPLETED && ((appUser.getEmail().equals(emailOrMobileNo) && appUser.isEmailVerified()) ||
                (appUser.getMobile().equals(emailOrMobileNo) && appUser.isMobileNoVerified()))){
            userActive = true;
        }

        return new UserPrincipal(appUser.getUserId(),appUser.getUserName(),appUser.getMobile(),appUser.getEmail(),appUser.getPassword(),grantedAuthorities,userActive);
    }
}
