package com.kr.setsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kr.setsu.service.ResumeService;

@RestController
@RequestMapping("/resume")
public class ResumeController {

	@Autowired
	ResumeService resumeService;
	@PostMapping("/upload")
	public boolean upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId)
			throws Exception {

		// 1. 校验文件类型
		if (!file.getOriginalFilename().endsWith(".xls")) {
			return false;
		}
		resumeService.insertResume(file, userId);
		return true;
	}
}
