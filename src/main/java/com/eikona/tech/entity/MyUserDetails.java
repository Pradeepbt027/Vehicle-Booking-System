package com.eikona.tech.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class MyUserDetails implements UserDetails{
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
	

	public MyUserDetails(User user) {
		this.userName = user.getUserName();
		this.password=user.getPassword();
		this.active=user.isActive();

        // Extract list of permissions (name)
       List<Privilege> privilegeList=user.getRole().getPrivileges();
       for(Privilege privilege:privilegeList) {
    	   GrantedAuthority authority = new SimpleGrantedAuthority(privilege.getName());
    	   this.authorities.add(authority);
       }
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}




	@Override
	public String getPassword() {
		return password;
	}




	@Override
	public String getUsername() {
		return userName;
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
		return active;
	}

	

}
