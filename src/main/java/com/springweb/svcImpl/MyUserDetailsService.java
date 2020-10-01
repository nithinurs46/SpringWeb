package com.springweb.svcImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springweb.dao.UserDao;
import com.springweb.model.Role;
import com.springweb.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = dao.getUser(userName);
		if (user == null)
			throw new UsernameNotFoundException(userName);

	     
		//return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(),
			//	grantedAuthorities);
		
		return new org.springframework.security.core.userdetails.User(
		          user.getUserId(), user.getPassword(), true, true, true, 
		          true, getAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			if (!isBlankString(role.getDeleteUserPriv())) {
				authorities.add(new SimpleGrantedAuthority("DELETE_USER_PRIVILEGE"));
			}
			if (!isBlankString(role.getUpdateUserPriv())) {
				authorities.add(new SimpleGrantedAuthority("EDIT_USER_PRIVILEGE"));
			}
			if (!isBlankString(role.getImportPriv())) {
				authorities.add(new SimpleGrantedAuthority("IMPORT_PRIVILEGE"));
			}

		}

		return authorities;
	}
	
	private void test(Role role) {
		
		

		
	}
	
	private boolean isBlankString(String string) {
	    return string == null || string.trim().isEmpty();
	}

}
