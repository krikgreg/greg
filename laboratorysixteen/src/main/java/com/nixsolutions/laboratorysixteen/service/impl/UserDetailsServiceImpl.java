package com.nixsolutions.laboratorysixteen.service.impl;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nixsolutions.laboratorysixteen.model.UserModel;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserManagementServiceImpl service;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserModel user = service.getUserModelByLogin(login);
		GrantedAuthority role = new SimpleGrantedAuthority(user.getRole().getName());
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(),
				user.getPassword(), Arrays.asList(role));
		return userDetails;
	}
}
