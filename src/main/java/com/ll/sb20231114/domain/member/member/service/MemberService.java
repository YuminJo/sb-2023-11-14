package com.ll.sb20231114.domain.member.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.repository.MemberRepository;
import com.ll.sb20231114.global.rsData.RsData;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RsData<Member> join(String username, String password) {
        if ( findByUsername(username).isPresent()) {
            return new RsData<>("F-1","이미 존재하는 회원입니다.");
        }

        password = passwordEncoder.encode(password);
        Member member = new Member(username, password);

        memberRepository.save(member);

        return new RsData<>("S-1","%s님 환영합니다.".formatted(member.getUsername()),member);
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

    public Optional<Member> findLatest() {
        return memberRepository.findLatest();
    }
}