package com.ll.sb20231114.global.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ll.sb20231114.global.rq.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NeedToLogoutInterceptor implements HandlerInterceptor {
	private final Rq rq;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (rq.isLogined()) {
			throw new RuntimeException("로그아웃 후 이용해주세요.");
		}
		return true;
	}
}