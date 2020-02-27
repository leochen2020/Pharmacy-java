package com.easy.auth;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.easy.data.entity.UserRole;

/**
 * @author jsbintask@foxmail.com
 * @date 2019/1/11 11:40
 */
public class AuthUser implements UserDetails {
    private String username;
    private String password;
    private List<UserRole> roles;

    public AuthUser(String username, String password, List<UserRole> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.isEmpty() ? Collections.EMPTY_LIST :
                // ROLE_ 是springsecurity对于角色的默认前缀，如果不加，验证会失败
                (roles.parallelStream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole())).collect(Collectors.toList()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
