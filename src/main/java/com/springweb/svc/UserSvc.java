package com.springweb.svc;

import java.util.List;
import java.util.Map;

import com.springweb.model.LoginForm;
import com.springweb.model.Role;
import com.springweb.model.User;

public interface UserSvc {

	void addUser(User form);

	void updateUser(User form);

	void deleteUser(String userId);

	User getUser(String userId);
	
	List<User> getAllUsers();
	
	boolean validateUser(LoginForm login);
	
	void addRole(Role form);
	
	void updateRole(Role form);

	void deleteRole(String roleName);

	Role getRole(String roleName);
	
	List<Role> getAllRoles();
	
	Map<String, String> getRoleNames();
	
	User getUserRoleList(User user);
	
	String[] getSelectedRolesForUser(User user);
	
}
