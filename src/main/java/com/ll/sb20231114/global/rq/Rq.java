package com.ll.sb20231114.global.rq;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestScope
@Component
public class Rq {
	private final HttpServletRequest req;
	private final HttpServletResponse resp;

	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public String redirect(String path,String msg) {
		msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);

		return "redirect:" + path + "?msg=" + msg;
	}
}