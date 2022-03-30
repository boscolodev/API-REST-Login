package com.boscolo.dev.Entities;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String username;
	@NotNull
	private String password;
	private Boolean loginStatus;

	public User(@NotNull String username, @NotNull String password) {
		this.username = username;
		this.password = password;
		this.loginStatus = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(username, user.username) && Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, password, loginStatus);

	}

}
