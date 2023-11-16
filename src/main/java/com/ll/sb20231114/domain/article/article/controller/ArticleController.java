package com.ll.sb20231114.domain.article.article.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ll.sb20231114.domain.article.article.entity.Article;
import com.ll.sb20231114.domain.article.article.service.ArticleService;
import com.ll.sb20231114.global.rq.Rq;
import com.ll.sb20231114.global.rsData.RsData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;
	private final Rq rq;

	@Data
	public static class WriteForm {
		@NotBlank
		private String title;
		@NotBlank
		private String body;
	}

	@GetMapping("/article/write")
	String showWrite() {
		return "article/write";
	}

	@PostMapping("/article/write")
	@ResponseBody
	RsData write(@Valid WriteForm writeForm) {
		Article article = articleService.write(writeForm.title, writeForm.body);

		RsData<Article> rs = new RsData<>("S-1", "%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);

		return rs;
	}

	@GetMapping("/article/list")
	String showList(Model model) {
		List<Article> articles = articleService.findAll();

		model.addAttribute("articles",articles);

		return "article/list";
	}

	@GetMapping("/article/getLastArticle")
	@ResponseBody
	Article getLastArticle() {
		return articleService.findLastArticle();
	}

	@GetMapping("/article/getArticles")
	@ResponseBody
	List<Article> getArticles() {
		return articleService.findAll();
	}

	@GetMapping("/article/articleServicePointer")
	@ResponseBody
	String articleServicePointer() {
		return articleService.toString();
	}

	// 이런것이 있었다.
	// 내부적으로 이런식으로 처리된다.
	@GetMapping("/article/httpServletRequestPointer")
	@ResponseBody
	String HttpServletRequestPointer(HttpServletRequest req) {
		return req.toString();
	}

	@GetMapping("/article/httpServletResponsePointer")
	@ResponseBody
	String HttpServletResponsePointer(HttpServletResponse resq) {
		return resq.toString();
	}

	@GetMapping("/article/rqPointer")
	@ResponseBody
	String rqPointer() {
		return rq.toString();
	}

	@GetMapping("/article/rqTest")
	String showRqTest(Model model) {
		String rqToString = rq.toString();

		model.addAttribute("rqToString", rqToString);
		return "article/rqTest";
	}
}