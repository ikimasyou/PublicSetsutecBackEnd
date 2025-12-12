package com.kr.setsu.entity;

public class MailTo {
	private String title;
	private String message;
	private String userId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "MailTo [title=" + title + ", message=" + message + ", userId=" + userId + "]";
	}
	
}
