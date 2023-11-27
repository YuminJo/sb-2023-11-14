package com.ll.sb20231114.domain.home.home.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AdmHomeControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("관리자가 아니라면 관리자 페이지에 접속할 수 없다.")
	@WithUserDetails("user1")
	void t1() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(get("/adm"))
			.andDo(print());

		// THEN
		resultActions
			.andExpect(status().is4xxClientError());
	}

	@Test
	@DisplayName("관리자라면 접속할 수 있다.")
	@WithUserDetails("admin")
	void t2() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(get("/adm"))
			.andDo(print());

		// THEN
		resultActions
			.andExpect(status().isOk())
			.andExpect(handler().handlerType(AdmHomeController.class))
			.andExpect(handler().methodName("showMain"))
			.andExpect(view().name("home/home/adm/main"));
	}

	@Test
	@DisplayName("/adm/home/about")
	@WithUserDetails("admin")
	void t3() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(get("/adm/home/about"))
			.andDo(print());

		// THEN
		resultActions
			.andExpect(status().isOk())
			.andExpect(handler().handlerType(AdmHomeController.class))
			.andExpect(handler().methodName("showAbout"))
			.andExpect(view().name("home/home/adm/about"));
	}
}