package com.nixsolutions.laboratorysixteen.model;

import javax.validation.constraints.Pattern;
import com.nixsolutions.laboratorysixteen.Constants;
import com.nixsolutions.laboratorysixteen.entity.Role;
import org.hibernate.validator.constraints.Email;

public class UserModel extends RecaptchaModel{

	private long id;
	@Pattern(regexp = Constants.LOGIN_PATTERN, message = "Format of the login is not valid, please try again.")
	private String login;
	@Pattern(regexp = Constants.PASSWORD_PATTERN, message = "The password must contains one digit, one lowercase characters, one uppercase characters, contains one special symbols '@#$%' - length at least 6 characters and maximum of 20")
	private String password;
	private String passwordAgain;
	@Email
	private String email;
	@Pattern(regexp = Constants.FIRSTNAME_PATTERN, message = "Format of the first name is not valid, please try again.")
	private String firstName;
	@Pattern(regexp = Constants.LASTNAME_PATTERN, message = "Format of the last name is not valid, please try again.")
	private String lastName;
	private int age;
	@Pattern(regexp = Constants.DATE_PATTERN, message = "Format of the birthday date is not valid, please try again.")
	private String birthday;
	private Role role;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPasswordAgain() {
		return passwordAgain;
	}
	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}
}