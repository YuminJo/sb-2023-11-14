package com.ll.sb20231114.domain.article.article.entity;

import com.ll.sb20231114.domain.member.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Article {
	@EqualsAndHashCode.Include
	private Long id;
	private Member author;
	private String title;
	private String body;

	public Article(Member author, String title, String body) {
		this.author = author;
		this.title = title;
		this.body = body;
	}
}