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

	@GetMapping("/article/write")
	String showWrite()
	{
		return "article/write";
	}

	private List<Article> articles = new ArrayList<>();

	@PostMapping("/article/write")
	@ResponseBody
	RsData<Article> doWrite(String title,String body) {
		Article article = new Article(articles.size(), title, body);
		articles.add(article);

		RsData<Article> rs = new RsData<>(
			"N-1",
			"%d번 게시물 작성".formatted(article.getId()),
			article
		);
		return rs;
	}

	@GetMapping("/article/getLastArticle")
	@ResponseBody
	Article getLastArticle() {
		return articles.getLast();
	}

	@GetMapping("/article/getArticles")
	@ResponseBody
	List<Article> getArticles()
	{
		return articles;
	}

}
