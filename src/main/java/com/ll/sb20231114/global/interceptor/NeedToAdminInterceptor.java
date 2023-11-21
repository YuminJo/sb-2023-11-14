package com.ll.sb20231114.global.interceptor;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ll.sb20231114.global.rq.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NeedToAdminInterceptor implements HandlerInterceptor {
	private final Rq rq;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		List<String> authorities = rq.getSessionAttr("authorities");

		if (!authorities.contains("ROLE_ADMIN")) {
			throw new RuntimeException("관리자만 이용할 수 있는 페이지 입니다.");
		}

		return true;
	}
}