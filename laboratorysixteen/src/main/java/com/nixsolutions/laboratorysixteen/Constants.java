package com.nixsolutions.laboratorysixteen;

public final class Constants {

	public static final String CURRENT_USERMODEL = "CURRENT_USERMODEL";
	public static final String USER_MANAGEMENT_SERVICE = "USER_MANAGEMENT_SERVICE";
	public static final String LIST_ROLES = "LIST_ROLES";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String DATE_PATTERN = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
	public static final String LOGIN_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
	public static final String FIRSTNAME_PATTERN = "[a-zA-Z]*";
	public static final String LASTNAME_PATTERN = "[a-zA-z]+([ '-][a-zA-Z]+)*";
	public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	public static final String CURRENT_PAGE = "CURRENT_PAGE";
}
