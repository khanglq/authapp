package com.khanglq.authapp.model.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "action")
public class Action implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String path;
	private Set<RoleAction> roleActions = new HashSet<RoleAction>(0);

	public Action() {
	}

	public Action(String path, Set<RoleAction> roleActions) {
		this.path = path;
		this.roleActions = roleActions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PATH", length = 150)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "action")
	public Set<RoleAction> getRoleActions() {
		return this.roleActions;
	}

	public void setRoleActions(Set<RoleAction> roleActions) {
		this.roleActions = roleActions;
	}

}
