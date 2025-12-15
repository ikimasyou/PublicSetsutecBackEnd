package com.kr.setsu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kr.setsu.entity.BPPersonnel;
import com.kr.setsu.service.serviceImpl.BPServiceImpl;

@RestController
@RequestMapping("/personal")
public class BPController {
	@Autowired
	BPServiceImpl bpServiceImpl;
	
	@PostMapping("/update")
	public void updateHandler(@RequestBody BPPersonnel bpPersonnel) {
		System.out.print(bpPersonnel.toString());
		int fg=bpServiceImpl.updateBP(bpPersonnel);
	}
}
