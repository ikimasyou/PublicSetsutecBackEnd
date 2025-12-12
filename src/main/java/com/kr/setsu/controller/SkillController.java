package com.kr.setsu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kr.setsu.entity.SkillCatogries;
import com.kr.setsu.entity.SkillUpdate;
import com.kr.setsu.service.SkillService;

@RestController
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	private SkillService skillService;

	@GetMapping("/getAllSkillCategriesByUserId")
	public List<SkillCatogries> getAllSkillCategriesByUserIdHandler(@RequestParam("userId") String userId){
		System.out.println("entered getAllSkillCategriesByUserIdHandler");
		return skillService.getAllSkillCatogriesByUserId(userId);
	}
	
	@PostMapping("/updateSkillCatogry")
	public boolean updateSkillCatogryHandler(@RequestBody SkillUpdate skillUpdate) {
		System.out.println("entered updateSkillCatogryHandler");
		return skillService.updateAllSkillCatogries(skillUpdate);
	}
}
