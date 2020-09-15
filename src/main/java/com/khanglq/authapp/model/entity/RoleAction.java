package com.khanglq.authapp.model.entity;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "role_action")
public class RoleAction implements java.io.Serializable {

	 
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Role role;
	private Action action;

	public RoleAction() {
	}

	public RoleAction(Role role, Action action) {
		this.role = role;
		this.action = action;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACTION_ID")
	public Action getAction() {
		return this.action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
