package com.javaweb.customexception;


// 1 exception dùng để báo lỗi khi bên FE gửi dữ liệu JSON bị null
public class FieldRequierdException extends RuntimeException {
	public FieldRequierdException(String s) {
		super(s);
	}
}
