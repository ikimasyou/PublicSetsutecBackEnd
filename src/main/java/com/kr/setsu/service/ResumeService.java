package com.kr.setsu.service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kr.setsu.entity.ResumeInfo;

public interface ResumeService {

	public int insertResume(MultipartFile file, String userId) throws Exception;
	
	public List<ResumeInfo> selectResumeByUserId(String userId);
	
	public int deleteResume(String userId,String fileName) throws IOException;
}
