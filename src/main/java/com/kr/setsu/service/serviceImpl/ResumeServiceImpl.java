package com.kr.setsu.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kr.setsu.entity.ResumeInfo;
import com.kr.setsu.mapper.ResumeMapper;
import com.kr.setsu.service.ResumeService;

@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	ResumeMapper resumeMapper;

	@Value("${resume.path}")
	private String basePath;

	@Override
	public int insertResume(MultipartFile file, String userId) throws Exception {
		// TODO Auto-generated method stub
		// 1. 拼接目录
		Path userDir = Paths.get(basePath, userId);
		Files.createDirectories(userDir);
		// 2. 拼接完整文件路径
		String fileName = file.getOriginalFilename();
		Path targetPath = userDir.resolve(fileName);
		// 3. 保存文件
		Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		// 4. 存数据库
		resumeMapper.insertResume(userId, targetPath.toString());
		return 0;
	}

	@Override
	public List<ResumeInfo> selectResumeByUserId(String userId) {
		// TODO Auto-generated method stub
		return resumeMapper.selectResumesByUserId(userId);
	}

	@Override
	public int deleteResume(String userId, String fileName) throws IOException {
		// TODO Auto-generated method stub
		Path filePath = Paths.get(basePath, userId, fileName);
		Files.deleteIfExists(filePath);
		resumeMapper.deleteResume(userId, filePath.toString());
		return 1;
	}

}
