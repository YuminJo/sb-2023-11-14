package com.ll.sb20231114.domain.member.member.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ll.sb20231114.domain.member.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	private final List<Member> members = new ArrayList<>();

	public Member save(Member member) {
		if (member.getId() == null) {
			member.setId(members.size() + 1L);
		}

		members.add(member);

		return member;
	}

	public List<Member> findAll() {
		return members;
	}

	public Optional<Member> findById(long id) {
		return members.stream()
			.filter(member -> member.getId() == id)
			.findFirst();
	}

	public Optional<Member> findByUsername(String username) {
		return members.stream()
			.filter(member -> member.getUsername().equals(username))
			.findFirst();
	}

	public void delete(long id) {
		members.removeIf(member -> member.getId() == id);
	}
}