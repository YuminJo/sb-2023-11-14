package com.ll.sb20231114.domain.article.article.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rsData.RsData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;

	@GetMapping("/article/write")
	String showWrite() {
		return "article/write";
	}

	@PostMapping("/article/write")
	@ResponseBody
	RsData write(
		String title,
		String body
	) {
		Article article = articleService.write(title, body);

		RsData<Article> rs = new RsData<>(
			"S-1",
			"%d번 게시물이 작성되었습니다.".formatted(article.getId()),
			article
		);

		return rs;
	}

	@PostMapping("/article/write2")
	@ResponseBody
	RsData write2(
		HttpServletRequest req,
		HttpServletResponse resp
	) {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		Article article = articleService.write(title, body);

		RsData<Article> rs = new RsData<>(
			"S-1",
			"%d번 게시물이 작성되었습니다.".formatted(article.getId()),
			article
		);

		return rs;
	}

	@GetMapping("/article/getLastArticle")
	@ResponseBody
	Article getLastArticle() {
		return articleService.findLastArticle();
	}

	@GetMapping("/article/getArticles")
	@ResponseBody
	List<Article> getArticles() {
		return articleService.findAll();
	}
}