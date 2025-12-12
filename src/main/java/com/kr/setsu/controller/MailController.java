package com.kr.setsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.setsu.entity.MailTo;
import com.kr.setsu.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;
	
	@PostMapping("/mailTo")
	public boolean mailToHandler(@RequestBody MailTo mailTo) {
		System.out.println("start mailto "+mailTo.toString());
		return mailService.mailTo(mailTo.getTitle(), mailTo.getMessage(),mailService.getMailAccountByUserId(mailTo.getUserId()));
	}
}
