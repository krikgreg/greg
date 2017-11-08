package com.nixsolutions.laboratoryseventeen;

public final class Constants {

	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String DATE_PATTERN = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
	public static final String LOGIN_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
	public static final String FIRSTNAME_PATTERN = "[a-zA-Z]*";
	public static final String LASTNAME_PATTERN = "[a-zA-z]+([ '-][a-zA-Z]+)*";
	public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
}
