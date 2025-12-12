package com.kr.setsu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kr.setsu.service.SkillService;

@SpringBootTest
public class SetsuTest {

	@Autowired
	private SkillService skillService;
	@Test
	public void test1() {
		skillService.getAllSkillCatogriesByUserId("U001").forEach(item->item.getSkills().forEach(i->System.out.println("skillName is "+i.getSkillName()+" skillLevel is "+i.getSelectedLevel())));
	}
}
