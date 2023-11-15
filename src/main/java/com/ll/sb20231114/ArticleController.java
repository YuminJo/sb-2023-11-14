package com.ll.sb20231114;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Controller
public class ArticleController {

	@GetMapping("/article/write")
	String showWrite()
	{
		return "article/write";
	}

	private List<Article> articles = new ArrayList<>();

	@GetMapping("/article/dowrite")
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

	@AllArgsConstructor
	@Getter
	class RsData<T>
	{
		private String resultcode;
		private String msg;
		private T data;
	}

	@AllArgsConstructor
	@Getter
	class Article {
		private long id;
		private String title;
		private String body;
	}
}
