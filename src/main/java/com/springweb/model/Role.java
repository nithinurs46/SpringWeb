package com.springweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "ESM_ROLE")
@Entity
@NamedQuery(
	    name = "Role.findAllRoles",
	    query = "SELECT r FROM Role r")
public class Role {

	public Role() {

	}

	@Id
	@Column(name = "ROLE_NAME")
	private String roleName;
	@Column(name = "UPDATE_USER_PRIV")
	private String updateUserPriv;
	@Column(name = "DELETE_USER_PRIV")
	private String deleteUserPriv;
	@Column(name = "IMPORT_PRIV")
	private String importPriv;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUpdateUserPriv() {
		return updateUserPriv;
	}

	public void setUpdateUserPriv(String updateUserPriv) {
		this.updateUserPriv = updateUserPriv;
	}

	public String getDeleteUserPriv() {
		return deleteUserPriv;
	}

	public void setDeleteUserPriv(String deleteUserPriv) {
		this.deleteUserPriv = deleteUserPriv;
	}

	public String getImportPriv() {
		return importPriv;
	}

	public void setImportPriv(String importPriv) {
		this.importPriv = importPriv;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
