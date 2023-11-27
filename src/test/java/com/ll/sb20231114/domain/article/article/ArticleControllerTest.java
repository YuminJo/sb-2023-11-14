package com.ll.sb20231114.domain.article.article;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticleControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("게시물 목록 페이지를 보여준다.")
	void t1() throws Exception {
		ResultActions resultActions = mvc
			.perform(get("/article/list"))
			.andDo(print());
	}
}
