package com.kr.setsu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kr.setsu.entity.SkillCatogries;
import com.kr.setsu.entity.SkillUpdate;

@Repository
public interface SkillMapper {
	public List<SkillCatogries> getSkillCategoryByUserId(String userId);
	public Integer updateSkillCatogory(SkillUpdate skillUpdate);
}
