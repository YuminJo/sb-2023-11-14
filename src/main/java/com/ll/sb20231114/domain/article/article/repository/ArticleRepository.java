package com.ll.sb20231114.domain.article.article.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ll.sb20231114.domain.article.article.entity.Article;

@Repository
public class ArticleRepository {
	private final List<Article> articles = new ArrayList<>();

	public Article save(Article article) {
		if (article.getId() == null)
		{
			article.setId(articles.size() + 1L);
		}

		articles.add(article);
		return article;
	}

	public Article findLastArticle() {
		return articles.getLast();
	}

	public List<Article> findAll() {
		return articles;
	}
}
