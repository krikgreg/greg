package com.nixsolutions.laboratoryseventeen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nixsolutions.laboratoryseventeen.entity.UserEntity;
import com.nixsolutions.laboratoryseventeen.model.CurrentUser;
import com.nixsolutions.laboratoryseventeen.service.UserManagementService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserManagementService service;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserEntity user = service.getUserModelByLogin(login);
		if(user != null) {
			return new CurrentUser(user);
		} else {
			throw new UsernameNotFoundException("Profile not found by" + login);
		}
	}
}
