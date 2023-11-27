package com.ll.sb20231114.domain.article.article.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ll.sb20231114.domain.article.article.entity.Article;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final List<Article> articles = new ArrayList<>();

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

    public void delete(Article article) {
        articles.remove(article);
    }

    public Optional<Article> findLatest() {
        return Optional.ofNullable(
            articles.isEmpty() ? null : articles.getLast()
        );
    }
}