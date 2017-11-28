package com.nixsolutions.laboratoryeighteen.entity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.nixsolutions.laboratoryeighteen.entity.RoleEntity;
import com.nixsolutions.laboratoryeighteen.service.UserManagementService;

public class FromStringIdToRoleConverter implements Converter<String, RoleEntity> {
	 
	 @Autowired
		private UserManagementService service;
	    
	    @Override
	    public RoleEntity convert(String id) {
	        long roleId = Long.parseLong(id);
	        return service.findRoleById(roleId);
	    }
 }

