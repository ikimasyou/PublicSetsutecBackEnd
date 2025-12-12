package com.kr.setsu.service;

import java.util.List;

import com.kr.setsu.entity.SkillCatogries;
import com.kr.setsu.entity.SkillUpdate;

public interface SkillService {

	public List<SkillCatogries> getAllSkillCatogriesByUserId(String userId);
	
	public boolean updateAllSkillCatogries(SkillUpdate skillUpdate);
}
