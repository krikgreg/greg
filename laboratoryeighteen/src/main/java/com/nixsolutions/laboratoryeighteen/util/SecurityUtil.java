package com.nixsolutions.laboratoryeighteen.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.nixsolutions.laboratoryeighteen.entity.UserEntity;
import com.nixsolutions.laboratoryeighteen.model.CurrentUser;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	public CurrentUser getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof CurrentUser) {
			return ((CurrentUser) principal);
		} else {
			return null;
		}
	}

	public Long getCurrentIdProfile() {
		CurrentUser currentProfile = getCurrentUser();
		return currentProfile != null ? currentProfile.getId() : null;
	}

	public void authentificate(UserEntity user) {
		CurrentUser currentUser = new CurrentUser(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(),
				currentUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}