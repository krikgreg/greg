package com.nixsolutions.laboratorysixteen.util;

import javax.servlet.http.HttpServletRequest;

import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.model.UserModel;

public class SessionUtils {

	public static UserModel getCurrentUserModel(HttpServletRequest req) {
		return (UserModel) req.getSession().getAttribute(Constants.CURRENT_USERMODEL);
	}
	
	public static boolean isCurrentUserLogin(HttpServletRequest req) {
		return getCurrentUserModel(req) != null;
	}
}
