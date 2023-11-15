package com.ll.sb20231114.global.app;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ll.sb20231114.domain.article.article.entity.Article;

@Configuration
public class AppConfig {
	@Bean
	List<Article> articles() {
		return new java.util.LinkedList<>();
	}
}
