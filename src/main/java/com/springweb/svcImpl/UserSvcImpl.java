package com.springweb.svcImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.springweb.dao.UserDao;
import com.springweb.model.LoginForm;
import com.springweb.model.Role;
import com.springweb.model.User;
import com.springweb.svc.UserSvc;

@Service
public class UserSvcImpl implements UserSvc {

	@Autowired
	UserDao dao;
	
	
	@Override
	public void addUser(User form) {
		form.setPassword(getHashPwd(form.getPassword()));
		dao.addUser(form);
		
	}

	@Override
	public void updateUser(User form) {
		dao.updateUser(form);
	}

	@Override
	public void deleteUser(String userId) {
		dao.deleteUser(userId);
	}

	@Override
	public User getUser(String userId) {
		return dao.getUser(userId);
	}
	
	@Override
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}
	
	public boolean validateUser(LoginForm login) {
		User userDetails = getUser(login.getUserId());
		if(userDetails==null) {
			return false;
		}
		
		boolean isValidUser = validatePwd(login.getPassword(), userDetails.getPassword());
		return isValidUser;
	}
	
	
	private String getHashPwd(String rawPwd) {
        String hashedPwd = BCrypt.hashpw(rawPwd, BCrypt.gensalt(12));
        return hashedPwd;
        
	}
	
	private boolean validatePwd(String rawPwd, String hashPwd) {
		boolean matched = BCrypt.checkpw(rawPwd, hashPwd);
		return matched;
        
	}

	@Override
	public void addRole(Role form) {
		dao.addRole(form);
		
	}

	@Override
	public void updateRole(Role form) {
		dao.updateRole(form);
		
	}

	@Override
	public void deleteRole(String roleName) {
		dao.deleteRole(roleName);
		
	}

	@Override
	public Role getRole(String roleName) {
		return dao.getRole(roleName);
	}

	@Override
	public List<Role> getAllRoles() {
		return dao.getAllRoles();
	}

	@Override
	public Map<String, String> getRoleNames() {
		List<Role> roles = dao.getAllRoles();
		Map<String, String> rolesMap = roles.stream().collect(Collectors.toMap(Role :: getRoleName, Role :: getRoleName));
		return rolesMap;
	}
	
	@Override
	public User getUserRoleList(User user){
		String[] roles = user.getSelectedRoles();
		if(roles==null || roles.length<=0) {
			return user;
		}
		for(int i=0;i<roles.length;i++) {
			Role userRole = new Role();
			userRole.setUser(user);
			userRole.setRoleName(roles[i]);
			user.getRoles().add(userRole);
		}
		return user;
		
	}
	
	public String[] getSelectedRolesForUser(User user) {
		List<String> selectedRoles = new ArrayList<String>();
		Set<Role> userRole = user.getRoles();
		userRole.forEach((role) -> { 
			selectedRoles.add((String)role.getRoleName());
		});		
		return selectedRoles.toArray(new String[selectedRoles.size()]);
	}
	
}
