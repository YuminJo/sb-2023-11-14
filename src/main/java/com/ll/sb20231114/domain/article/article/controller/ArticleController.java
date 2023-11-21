package com.ll.sb20231114.domain.article.article.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.domain.member.member.entity.Member;
import com.ll.sb20231114.domain.member.member.service.MemberService;
import com.ll.sb20231114.global.rq.Rq;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;
	private final MemberService memberService;
	private final Rq rq;

	@GetMapping("/article/list")
	String showList(Model model) {
		List<Article> articles = articleService.findAll();

		model.addAttribute("articles", articles);

		return "article/article/list";
	}

	@GetMapping("/article/detail/{id}")
	String showDetail(Model model, @PathVariable long id) {
		Article article = articleService.findById(id).get();

		model.addAttribute("article", article);

		return "article/article/detail";
	}

	@GetMapping("/article/write")
	String showWrite() {
		if (!rq.isLogined())
			throw new RuntimeException("로그인 후 이용해주세요.");

		return "article/article/write";
	}

	@Data
	public static class WriteForm {
		@NotBlank
		private String title;
		@NotBlank
		private String body;
	}

	@PostMapping("/article/write")
	@SneakyThrows
	String write(@Valid WriteForm writeForm) {
		if (!rq.isLogined())
			throw new RuntimeException("로그인 후 이용해주세요.");

		Member loginedMember = rq.getMember();
		Article article = articleService.write(loginedMember, writeForm.title, writeForm.body);

		return rq.redirect("/article/list", "%d번 게시물 생성되었습니다.".formatted(article.getId()));
	}

	@GetMapping("/article/modify/{id}")
	String showModify(Model model, @PathVariable long id) {
		if (!rq.isLogined())
			throw new RuntimeException("로그인 후 이용해주세요.");

		Article article = articleService.findById(id).get();

		model.addAttribute("article", article);

		return "article/article/modify";
	}

	@Data
	public static class ModifyForm {
		@NotBlank
		private String title;
		@NotBlank
		private String body;
	}

	@PostMapping("/article/modify/{id}")
	String modify(@PathVariable long id, @Valid ModifyForm modifyForm) {
		if (!rq.isLogined())
			throw new RuntimeException("로그인 후 이용해주세요.");

		articleService.modify(id, modifyForm.title, modifyForm.body);

		return rq.redirect("/article/list", "%d번 게시물 수정되었습니다.".formatted(id));
	}

	@GetMapping("/article/delete/{id}")
	String delete(@PathVariable long id) {
		if (!rq.isLogined())
			throw new RuntimeException("로그인 후 이용해주세요.");

		articleService.delete(id);

		return rq.redirect("/article/list", "%d번 게시물 삭제되었습니다.".formatted(id));
	}
}