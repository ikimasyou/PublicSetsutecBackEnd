package com.kr.setsu.service;

import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

	public int insertResume(MultipartFile file, String userId) throws Exception;
}
