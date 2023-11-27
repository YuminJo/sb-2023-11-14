package com.ll.sb20231114.domain.article.article;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ll.sb20231114.domain.article.article.controller.ArticleController;
import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticleControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ArticleService articleService;

	// GET /article/list
	@Test
	@DisplayName("게시물 목록 페이지를 보여준다")
	void t1() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(get("/article/list"))
			.andDo(print());

		// THEN
		resultActions
			.andExpect(status().is2xxSuccessful())
			.andExpect(handler().handlerType(ArticleController.class))
			.andExpect(handler().methodName("showList"))
			.andExpect(content().string(containsString("""
                        게시글 목록
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        3번 : 제목3
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        2번 : 제목2
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        1번 : 제목1
                        """.stripIndent().trim())));
	}

	// GET /article/detail/{id}
	@Test
	@DisplayName("게시물 내용 페이지를 보여준다")
	void t2() throws Exception {
		// WHEN
		ResultActions resultActions = mvc
			.perform(get("/article/detail/1"))
			.andDo(print());

		Article article = articleService.findById(1L).get();

		// THEN
		resultActions
			.andExpect(status().is2xxSuccessful())
			.andExpect(handler().handlerType(ArticleController.class))
			.andExpect(handler().methodName("showDetail"))
			.andExpect(content().string(containsString("""
                        게시글 내용
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString("""
                        <div class="badge badge-outline">1</div>
                        """.stripIndent().trim())))
			.andExpect(content().string(containsString(article.getTitle())))
			.andExpect(content().string(containsString(article.getBody())));
	}
}
