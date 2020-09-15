package com.khanglq.authapp.model.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "groups")
public class Groups implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private Integer id;
	private String groupName;
	private Set<GroupUser> groupUsers = new HashSet<GroupUser>(0);
	private Set<GroupRole> groupRoles = new HashSet<GroupRole>(0);

	public Groups() {
	}

	public Groups(String groupName, Set<GroupUser> groupUsers,
				  Set<GroupRole> roleActions) {
		this.groupName = groupName;
		this.groupUsers = groupUsers;
		this.groupRoles = roleActions;
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

	@Column(name = "GROUP_NAME", length = 20)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "groups")
	public Set<GroupUser> getGroupUsers() {
		return this.groupUsers;
	}

	public void setGroupUsers(Set<GroupUser> groupUsers) {
		this.groupUsers = groupUsers;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "groups")
	public Set<GroupRole> getGroupRoles() {
		return this.groupRoles;
	}

	public void setGroupRoles(Set<GroupRole> groupRoles) {
		this.groupRoles = groupRoles;
	}

}
