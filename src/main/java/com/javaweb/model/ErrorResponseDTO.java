package com.javaweb.model;

import java.util.ArrayList;
import java.util.List;
//Trả về chi tiết lỗi trong nội dung phản hồi bên postman
public class ErrorResponseDTO {
	private String error;
	private List<String> detail = new ArrayList<>();
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getDetail() {
		return detail;
	}
	public void setDetail(List<String> detail) {
		this.detail = detail;
	}
	
}
