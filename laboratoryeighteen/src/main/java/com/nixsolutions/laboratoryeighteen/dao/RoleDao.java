package com.nixsolutions.laboratoryeighteen.dao;

import java.util.List;

import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;

public interface RoleDao {

	public void create(RoleEntity role);

	public void update(RoleEntity role);

	public void remove(RoleEntity role);

	public RoleEntity findByName(String name);

	public RoleEntity findRoleById(Long id);

	List<RoleEntity> findAll();

}
