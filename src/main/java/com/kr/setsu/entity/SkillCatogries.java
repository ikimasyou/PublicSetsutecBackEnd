package com.kr.setsu.entity;

import java.util.List;

public class SkillCatogries {
	
	private String skillCatogry;
	private List<SkillInfo> skills;
	@Override
	public String toString() {
		return "SkillCatogries [skillCatogry=" + skillCatogry + ", skills=" + skills + "]";
	}
	public String getSkillCatogry() {
		return skillCatogry;
	}
	public void setSkillCatogry(String skillCatogry) {
		this.skillCatogry = skillCatogry;
	}
	public List<SkillInfo> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillInfo> skills) {
		this.skills = skills;
	}
	
}
