package com.springweb.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springweb.dao.UserDao;
import com.springweb.model.Role;
import com.springweb.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addUser(User form) {
		// em.getTransaction().begin();
		try {
			em.persist(form);
			// em.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			e.getConstraintViolations()
					.forEach(err -> System.out.println("ConstraintViolationException:" + err.toString()));
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(User form) {
		// em.getTransaction().begin();
		User user = getUser(form.getUserId());
		user.setPassword(form.getPassword());
		user.setMobileNo(form.getMobileNo());
		em.merge(form);
		// em.getTransaction().commit();

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String userId) {
		User user = getUser(userId);
		em.remove(user);
	}

	@Override
	public User getUser(String userId) {
		User user = em.find(User.class, userId);
		return user;
	}

	public List<User> getAllUsers() {
		List<User> users = em.createNamedQuery("User.findAllUsers").getResultList();
		return users;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addRole(Role form) {
		try {
			em.persist(form);
		} catch (ConstraintViolationException e) {
			e.getConstraintViolations()
					.forEach(err -> System.out.println("ConstraintViolationException:" + err.toString()));
		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRole(Role form) {
		Role role = getRole(form.getRoleName());
		role.setDeleteUserPriv(form.getDeleteUserPriv());
		role.setImportPriv(form.getImportPriv());
		role.setUpdateUserPriv(form.getUpdateUserPriv());
		em.merge(form);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteRole(String roleName) {
		Role role = getRole(roleName);
		em.remove(role);

	}

	@Override
	public Role getRole(String roleName) {
		return em.find(Role.class, roleName);
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = em.createNamedQuery("Role.findAllRoles").getResultList();
		return roles;
	}
}
