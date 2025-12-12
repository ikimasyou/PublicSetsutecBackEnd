package com.kr.setsu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kr.setsu.entity.SkillCatogries;
import com.kr.setsu.entity.SkillUpdate;
import com.kr.setsu.mapper.SkillMapper;
import com.kr.setsu.service.SkillService;

@Service
public class SkillServiceImpl  implements SkillService{

	@Autowired
	private SkillMapper skillMapper;
	@Override
	public List<SkillCatogries> getAllSkillCatogriesByUserId(String userId) {
		// TODO Auto-generated method stub
		return skillMapper.getSkillCategoryByUserId(userId);
	}

	@Override
	public boolean updateAllSkillCatogries(SkillUpdate skillUpdate) {
		// TODO Auto-generated method stub
		if(skillMapper.updateSkillCatogory(skillUpdate)>0) {
			return true;
		}
		return false;
	}

}
