package com.ll.sb20231114.domain.member.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;

	public Member join(String username, String password) {
		Member member = new Member(username, password);

		memberRepository.save(member);

		return member;
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	public Optional<Member> findById(long id) {
		return memberRepository.findById(id);
	}

	public Optional<Member> findByUsername(String username) {
		return memberRepository.findByUsername(username);
	}

	public void delete(long id) {
		memberRepository.delete(id);
	}

	public void modify(long id, String username, String password) {
		Member member = findById(id).get();
		member.setUsername(username);
		member.setPassword(password);
	}
}