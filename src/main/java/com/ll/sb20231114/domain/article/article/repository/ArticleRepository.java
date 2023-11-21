package com.ll.sb20231114.domain.article.article.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.repository.MemberRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
	private final MemberRepository memberRepository;
	private final List<Article> articles = new ArrayList<>();

	@PostConstruct
	void init() {

		Member member1 = memberRepository.findById(1L).get();
		Member member2 = memberRepository.findById(2L).get();

		save(new Article(member1, "title1","content1"));
		save(new Article(member2, "title2","content2"));
	}

	public Article save(Article article) {
		if (article.getId() == null) {
			article.setId(articles.size() + 1L);
		}

		articles.add(article);

		return article;
	}

	public List<Article> findAll() {
		return articles;
	}

	public Optional<Article> findById(long id) {
		return articles.stream()
			.filter(article -> article.getId() == id)
			.findFirst();
	}

	public void delete(long id) {
		articles.removeIf(article -> article.getId() == id);
	}
}