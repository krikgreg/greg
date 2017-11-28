package com.nixsolutions.laboratoryeighteen.entity;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import com.nixsolutions.laboratoryeighteen.Constants;
import com.nixsolutions.laboratoryeighteen.entity.converter.StringSqlDateConverter;

@Entity
@Table(name = "Users")
public class UserEntity {

	private long id;
	@Pattern(regexp=Constants.LOGIN_PATTERN)
	private String login;
	@Pattern(regexp=Constants.PASSWORD_PATTERN)
	private String password;
	private String passwordAgain;
	@Email
	private String email;
	@Pattern(regexp=Constants.FIRSTNAME_PATTERN)
	private String firstName;
	@Pattern(regexp=Constants.LASTNAME_PATTERN)
	private String lastName;
	@Pattern(regexp=Constants.DATE_PATTERN)
	private String birthday;
	private RoleEntity role;
	private int age;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public void setAge(int age) {
		this.age = age;
	}

	public UserEntity() {
	}

	public UserEntity(long id, String login, String password, String email, String firstName, String lastName,
			String birthday, RoleEntity role) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	@OneToOne
	@JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE", nullable = false)
	public RoleEntity getRole() {
		return role;
	}

	public RoleEntity setRole(RoleEntity role) {
		return this.role = role;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "LOGIN", unique = true, nullable = false)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "PASSWORD", unique = false, nullable = false)
	public String getPassword() {
		return password;
	}

	public int getAge() {
		LocalDate now = LocalDate.now();
		LocalDate localDateBirthday = LocalDate.parse(birthday, formatter);
		Period period = Period.between(localDateBirthday, now);
		age = period.getYears();
		return age;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL", unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FIRSTNAME", unique = false, nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LASTNAME", unique = false, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "BIRTHDAY", unique = false, nullable = false)
	@Convert(converter = StringSqlDateConverter.class)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birhday) {
		this.birthday = birhday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}
}