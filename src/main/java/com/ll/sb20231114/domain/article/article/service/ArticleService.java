package com.ll.sb20231114.domain.article.article.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service // 저는 단 한번만 생성되고, 그 이후에는 재사용되는 객체입니다.
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;

	public Article write(String title, String body) {
		Article article = new Article(title, body);

		articleRepository.save(article);

		return article;
	}

	public Article findLastArticle() {
		return articleRepository.findLastArticle();
	}

	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	public Optional<Article> findById(long id) {
		return articleRepository.findById(id);
	}
}