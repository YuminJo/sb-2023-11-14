package com.ll.sb20231114.domain.article.article.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.repository.ArticleRepository;
import com.ll.sb20231114.domain.member.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service // 저는 단 한번만 생성되고, 그 이후에는 재사용되는 객체입니다.
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;

	public Article write(Member author, String title, String body) {
		Article article = new Article(author, title, body);

		articleRepository.save(article);

		return article;
	}

	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	public Optional<Article> findById(long id) {
		return articleRepository.findById(id);
	}

	public void delete(long id) {
		articleRepository.delete(id);
	}

	public Article modify(long id, String title, String body) {
		Article article = findById(id).get();
		article.setTitle(title);
		article.setBody(body);

		return article;
	}
}