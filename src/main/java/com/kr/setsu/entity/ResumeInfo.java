package com.kr.setsu.entity;

import java.time.LocalDateTime;

public class ResumeInfo {

	public String filepath;
	public String userId;
	public LocalDateTime updatedAt;
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "ResumeInfo [filepath=" + filepath + ", userId=" + userId + ", updatedAt=" + updatedAt + "]";
	}

	
}
