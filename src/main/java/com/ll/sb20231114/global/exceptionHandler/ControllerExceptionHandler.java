package com.ll.sb20231114.global.exceptionHandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ll.sb20231114.global.rq.Rq;

import lombok.RequiredArgsConstructor;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class ControllerExceptionHandler {
	private final Rq rq;

	@ExceptionHandler(RuntimeException.class)
	public String handleException(RuntimeException ex) {
		return rq.historyBack(ex.getMessage());
	}
}
