package com.nixsolutions.laboratorysixteen.entity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.nixsolutions.laboratorysixteen.entity.Role;
import com.nixsolutions.laboratorysixteen.service.UserManagementService;

public class FromStringIdToRoleConverter implements Converter<String, Role> {
	 
	 @Autowired
		private UserManagementService service;
	    
	    @Override
	    public Role convert(String id) {
	        long roleId = Long.parseLong(id);
	        return service.findRoleById(roleId);
	    }
 }

