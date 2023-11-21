package com.ll.sb20231114.domain.member.member.entity;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Member {
	@EqualsAndHashCode.Include
	private Long id;
	private String username;
	private String password;

	public Member(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean isAdmin() {
		return username.equals("admin");
	}

	public List<SimpleGrantedAuthority> getAuthorities() {
		if (isAdmin()) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_MEMBER"));
		}

		return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));
	}
}