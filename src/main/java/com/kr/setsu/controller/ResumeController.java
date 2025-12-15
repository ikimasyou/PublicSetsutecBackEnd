package com.kr.setsu.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kr.setsu.entity.ResumeInfo;
import com.kr.setsu.service.ResumeService;

@RestController
@RequestMapping("/resume")
public class ResumeController {

	@Autowired
	ResumeService resumeService;

	@PostMapping("/upload")
	public boolean upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId)
			throws Exception {
		System.out.println("entered upload");
		// 1. 校验文件类型
		if (!file.getOriginalFilename().endsWith(".xls") && !file.getOriginalFilename().endsWith(".xlsx")) {
			return false;
		}

		resumeService.insertResume(file, userId);
		return true;
	}

	@GetMapping("/getResumes")
	public List<ResumeInfo> getResumesHandler(@RequestParam String userId) {
		System.out.println("entered getResumes");
		return resumeService.selectResumeByUserId(userId);
	}

	@DeleteMapping("/deleteResume")
	public int deleteResumeHandler(@RequestParam("userId") String userId, @RequestParam("fileName") String fileName)
			throws IOException {
		System.out.println("this is userId " + userId);
		System.out.println("this is fileName " + fileName);
		return resumeService.deleteResume(userId, fileName);
	}
}
