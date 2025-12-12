package com.kr.setsu.entity;

public class SkillInfo {
	private String skillName;
	private Integer selectedLevel;
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Integer getSelectedLevel() {
		return selectedLevel;
	}
	@Override
	public String toString() {
		return "SkillInfo [skillName=" + skillName + ", selectedLevel=" + selectedLevel + "]";
	}
	public void setSelectedLevel(Integer selectedLevel) {
		this.selectedLevel = selectedLevel;
	}
}
