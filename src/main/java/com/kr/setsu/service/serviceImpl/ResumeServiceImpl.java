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

	/*
	 * @Override public int insertResume(MultipartFile file, String userId) throws
	 * Exception { // TODO Auto-generated method stub // 1. 拼接目录 Path userDir =
	 * Paths.get(basePath, userId); Files.createDirectories(userDir); // 2. 拼接完整文件路径
	 * String fileName = file.getOriginalFilename(); Path targetPath =
	 * userDir.resolve(fileName); // 3. 保存文件 Files.copy(file.getInputStream(),
	 * targetPath, StandardCopyOption.REPLACE_EXISTING); // 4. 存数据库
	 * resumeMapper.insertResume(userId, targetPath.toString()); return 0; }
	 */

	@Override
	public int insertResume(MultipartFile file, String userId) throws Exception {
		// 1. 获取当前时间（精确到秒）
		LocalDateTime now = LocalDateTime.now();

		// 2. 构造目录：basePath/userId/yyyy-MM-dd/
		String dateDir = now.toLocalDate().toString(); // e.g. 2025-12-16
		Path userDir = Paths.get(basePath, userId, dateDir);
		Files.createDirectories(userDir);

		// 3. 构造完整文件路径
		String fileName = file.getOriginalFilename();
		Path targetPath = userDir.resolve(fileName);

		// 4. 保存文件（当天同名覆盖）
		Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

		// 5. 存数据库（手动插入时间）
		resumeMapper.insertResume(userId, targetPath.toString(), now);

		return 0;
	}

	@Override
	public Path getResumePath(String userId, LocalDateTime updatedAt) {
		String filepath = resumeMapper.selectFilePathByUserAndTime(userId, updatedAt);
		if (filepath == null) {
			return null;
		}
		return Paths.get(filepath);
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
