package com.nixsolutions.laboratoryeighteen.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;
import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;

public class CurrentUser extends User  {
	private static final long serialVersionUID = -3250953033560579950L;
	private long id;
	private String firstName;
	private RoleEntity role;

	public CurrentUser(UserEntity user) {
		super(user.getLogin(), user.getPassword(), true, true, true, true, Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName())));
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.role = user.getRole();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}
}