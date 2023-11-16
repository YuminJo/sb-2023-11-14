package com.ll.sb20231114.domain.article.article.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ll.sb20231114.domain.article.article.entity.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
	private final List<Article> articles = new ArrayList<>() {{
		add(new Article(1L, "제목 1", "내용 1"));
		add(new Article(2L, "제목 2", "내용 2"));
		add(new Article(3L, "제목 3", "내용 3"));
	}};

	public Article save(Article article) {
		if (article.getId() == null) {
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