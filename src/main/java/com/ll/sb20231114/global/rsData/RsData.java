package com.ll.sb20231114.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData<T> {
	private String resultCode;
	private String msg;
	private T data;
}
