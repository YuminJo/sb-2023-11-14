package com.ll.sb20231114.domain.member.member.controller;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MemberControllerTest {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// GET /member/login
	@Test
	@DisplayName("로그인 페이지를 보여준다")
	void t1() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(get("/member/login"))
			.andDo(print());

		// THEN
		resultActions
			.andExpect(status().isOk())
			.andExpect(view().name("member/member/login"))
			.andExpect(handler().handlerType(MemberController.class))
			.andExpect(handler().methodName("showLogin"))
			.andExpect(content().string(containsString("""
                        로그인
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        <input type="text" name="username"
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        <input type="password" name="password"
                        """.stripIndent().trim())));
	}

	// POST /member/login
	@Test
	@DisplayName("잘못된 로그인 정보")
	void t2() throws Exception {
		mvc.perform(
				formLogin("/member/login")
					.user("username", "user1")
					.password("password", "12345")
			)
			.andDo(print())
			.andExpect(unauthenticated());
	}

	// POST /member/login
	@Test
	@DisplayName("로그인 처리")
	void t3() throws Exception {
		mvc.perform(
				formLogin("/member/login")
					.user("username", "user1")
					.password("password", "1234")
			)
			.andDo(print())
			.andExpect(authenticated());
	}

	// GET /member/login
	@Test
	@DisplayName("회원가입 폼")
	void t4() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(get("/member/join"))
			.andDo(print());

		// THEN
		resultActions
			.andExpect(status().isOk())
			.andExpect(view().name("member/member/join"))
			.andExpect(handler().handlerType(MemberController.class))
			.andExpect(handler().methodName("showJoin"))
			.andExpect(content().string(containsString("""
                        회원가입
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        <input type="text" name="username"
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        <input type="password" name="password"
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        <input type="password" name="passwordConfirm"
                        """.stripIndent().trim())));
	}

	// POST /article/write
	@Test
	@DisplayName("회원가입 폼 처리")
	void t5() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(
				post("/member/join")
					.with(csrf())
					.param("username", "usernew")
					.param("password", "1234")
			)
			.andDo(print());

		// THEN
		resultActions
			.andExpect(status().is3xxRedirection())
			.andExpect(handler().handlerType(MemberController.class))
			.andExpect(handler().methodName("join"))
			.andExpect(redirectedUrlPattern("/member/login?msg=**"));

		Member member = memberService.findLatest().get();

		assertThat(member.getUsername()).isEqualTo("usernew");
		assertThat(passwordEncoder.matches("1234", member.getPassword())).isTrue();
	}
}