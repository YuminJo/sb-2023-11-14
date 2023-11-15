package com.ll.sb20231114.domain.article.article.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.global.rsData.RsData;

@Controller
public class ArticleController {
	private List<Article> articles = new ArrayList<>();

	@GetMapping("/article/write")
	String showWrite() {
		return "article/write";
	}

	@PostMapping("/article/write")
	@ResponseBody
	RsData doWrite(
		String title,
		String body
	) {
		Article article = new Article(articles.size() + 1, title, body);
		articles.add(article);

		RsData<Article> rs = new RsData<>(
			"S-1",
			"%d번 게시물이 작성되었습니다.".formatted(article.getId()),
			article
		);

		String resultCode = rs.getResultCode();
		String msg = rs.getMsg();
		Article _article = rs.getData();

		return rs;
	}

	@GetMapping("/article/getLastArticle")
	@ResponseBody
	Article getLastArticle() {
		return articles.getLast();
	}

	@GetMapping("/article/getArticles")
	@ResponseBody
	List<Article> getArticles() {
		return articles;
	}
}

