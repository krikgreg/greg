package com.nixsolutions.laboratorysixteen.dao;

import java.util.List;

import com.nixsolutions.laboratorysixteen.entity.Role;

public interface RoleDao {

	public void create(Role role);

	public void update(Role role);

	public void remove(Role role);

	public Role findByName(String name);

	public Role findRoleById(Long id);

	List<Role> findAll();

}
