package com.springweb;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.springweb.dao.UserDao;
import com.springweb.model.Role;
import com.springweb.model.User;
import com.springweb.svcImpl.UserSvcImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestRoles {

	@InjectMocks
	UserSvcImpl userSvcMock;

	@Mock
	UserDao dao;

	@Test
	public void testGetAllRoles() {
		List<Role> roles = new ArrayList<Role>();
		User user1 = new User();
		user1.setUserId("A");
		user1.setAddress1("Address1");
		user1.setAddress2("Address2");
		user1.setCity("City11");
		Role role1 = new Role();
		role1.setRoleName("Role-Name-1");
		role1.setDeleteUserPriv("1");
		role1.setImportPriv("1");
		role1.setUpdateUserPriv("0");
		role1.setUser(user1);

		User user2 = new User();
		user2.setUserId("AB");
		user2.setAddress1("Address11");
		user2.setAddress2("Address22");
		user2.setCity("City21");
		Role role2 = new Role();
		role2.setRoleName("Role-Name-2");
		role2.setDeleteUserPriv("0");
		role2.setImportPriv("0");
		role2.setUpdateUserPriv("1");
		role2.setUser(user2);

		roles.add(role1);
		roles.add(role2);

		when(dao.getAllRoles()).thenReturn(roles);

		List<Role> allRoles = userSvcMock.getAllRoles();
		assertEquals(2, allRoles.size());
		verify(dao, times(1)).getAllRoles();
		assertEquals(allRoles.get(0).getUser().getUserId(), "A");

	}

	@Test
	public void deleteRole() {
		List<Role> roles = new ArrayList<Role>();
		Role role1 = new Role();
		role1.setRoleName("Role-Name-1");
		role1.setDeleteUserPriv("1");
		role1.setImportPriv("1");
		role1.setUpdateUserPriv("0");
		User user1 = new User();
		user1.setUserId("A");
		user1.setAddress1("Address1");
		user1.setAddress2("Address2");
		user1.setCity("City11");
		role1.setUser(user1);

		Role role2 = new Role();
		role2.setRoleName("Role-Name-2");
		role2.setDeleteUserPriv("0");
		role2.setImportPriv("0");
		role2.setUpdateUserPriv("1");
		User user2 = new User();
		user2.setUserId("AB");
		user2.setAddress1("Address11");
		user2.setAddress2("Address22");
		user2.setCity("City21");
		role2.setUser(user2);

		roles.add(role1);
		roles.add(role2);
		userSvcMock.deleteRole("Role-Name-2");
		verify(dao, times(1)).deleteRole("Role-Name-2");

	}
	
	@Test
	public void updateRole() {
		Role role1 = new Role();
		role1.setRoleName("Role-Name-1");
		role1.setDeleteUserPriv("1");
		role1.setImportPriv("1");
		role1.setUpdateUserPriv("0");
		User user1 = new User();
		user1.setUserId("A");
		user1.setAddress1("Address1");
		user1.setAddress2("Address2");
		user1.setCity("City11");
		role1.setUser(user1);

		userSvcMock.updateRole(role1);
		verify(dao, times(1)).updateRole(role1);
		
	}
	
	@Test
	public void testGetRole() {
		Role role1 = new Role();
		role1.setRoleName("Role-Name-1");
		role1.setDeleteUserPriv("1");
		role1.setImportPriv("1");
		role1.setUpdateUserPriv("0");
		User user1 = new User();
		user1.setUserId("A");
		user1.setAddress1("Address1");
		user1.setAddress2("Address2");
		user1.setCity("City11");
		role1.setUser(user1);
		
		when(dao.getRole("Role-Name-1")).thenReturn(role1);
		Role result = userSvcMock.getRole("Role-Name-1");
		assertEquals("1", result.getImportPriv());
		
	}

}
