package com.kr.setsu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@Mapper
public interface ResumeMapper {
	@Insert("INSERT INTO bp_resume(user_id, filepath) VALUES (#{userId}, #{filepath})")
	int insertResume(@Param("userId") String userId, @Param("filepath") String filepath);
}
