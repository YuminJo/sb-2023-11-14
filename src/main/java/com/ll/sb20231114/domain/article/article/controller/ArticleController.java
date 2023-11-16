package com.ll.sb20231114.domain.article.article.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;

	@GetMapping("/article/list")
	String showList(Model model) {
		List<Article> articles = articleService.findAll();

		model.addAttribute("articles", articles);

		return "article/list";
	}

	@GetMapping("/article/detail/{id}")
	String showDetail(Model model, @PathVariable long id) {
		Article article = articleService.findById(id).get();

		model.addAttribute("article", article);

		return "article/detail";
	}

	@GetMapping("/article/write")
	String showWrite() {
		return "article/write";
	}

	@Data
	public static class WriteForm {
		@NotBlank
		private String title;
		@NotBlank
		private String body;
	}

	@PostMapping("/article/write")
	String write(@Valid WriteForm writeForm) {
		Article article = articleService.write(writeForm.title, writeForm.body);

		String msg = "id %d, article created".formatted(article.getId());

		return "redirect:/article/list?msg=" + msg;
	}

	@GetMapping("/article/modify/{id}")
	String showModify(Model model, @PathVariable long id) {
		Article article = articleService.findById(id).get();

		model.addAttribute("article", article);

		return "article/modify";
	}

	@Data
	public static class ModifyForm {
		@NotBlank
		private String title;
		@NotBlank
		private String body;
	}

	@PostMapping("/article/modify/{id}")
	String write(@PathVariable long id, @Valid ModifyForm modifyForm) {
		articleService.modify(id, modifyForm.title, modifyForm.body);

		String msg = "id %d, article modified".formatted(id);

		return "redirect:/article/list?msg=" + msg;
	}

	@GetMapping("/article/delete/{id}")
	String delete(@PathVariable long id) {
		articleService.delete(id);

		String msg = "id %d, article deleted".formatted(id);

		return "redirect:/article/list?msg=" + msg;
	}
}