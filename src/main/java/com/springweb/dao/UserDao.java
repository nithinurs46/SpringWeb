package com.springweb.dao;

import java.util.List;

import com.springweb.model.Role;
import com.springweb.model.User;

public interface UserDao {

	void addUser(User form);

	void updateUser(User form);

	void deleteUser(String userId);

	User getUser(String userId);
	
	List<User> getAllUsers();
	
	void addRole(Role form);
	
	void updateRole(Role form);

	void deleteRole(String roleName);

	Role getRole(String roleName);
	
	List<Role> getAllRoles();
}
