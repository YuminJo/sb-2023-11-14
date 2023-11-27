package com.ll.sb20231114.domain.article.article.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.repository.ArticleRepository;
import com.ll.sb20231114.domain.member.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
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

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public void modify(Article article, String title, String body) {
        article.setTitle(title);
        article.setBody(body);
    }

    public boolean canModify(Member actor, Article article) {
        if (actor == null) return false;

        return article.getAuthor().equals(actor);
    }

    public boolean canDelete(Member actor, Article article) {
        if (actor == null) return false;

        if (actor.isAdmin()) return true;

        return article.getAuthor().equals(actor);
    }

    public Optional<Article> findLatest() {
        return articleRepository.findLatest();
    }
}