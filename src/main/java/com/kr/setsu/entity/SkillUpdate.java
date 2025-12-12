package com.kr.setsu.entity;

public class SkillUpdate {

	private String userId;
	private String skillCatogry;
	private String skillName;
	private Integer selectedLevel;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSkillCatogry() {
		return skillCatogry;
	}
	public void setSkillCatogry(String skillCatogry) {
		this.skillCatogry = skillCatogry;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Integer getSelectedLevel() {
		return selectedLevel;
	}
	public void setSelectedLevel(Integer selectedLevel) {
		this.selectedLevel = selectedLevel;
	}
	@Override
	public String toString() {
		return "SkillUpdate [userId=" + userId + ", skillCatogry=" + skillCatogry + ", skillName=" + skillName
				+ ", selectedLevel=" + selectedLevel + "]";
	}
	
}
