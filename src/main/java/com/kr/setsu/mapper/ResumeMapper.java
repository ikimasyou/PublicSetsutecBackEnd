package com.kr.setsu.mapper;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.kr.setsu.entity.ResumeInfo;

@Repository
@Mapper
public interface ResumeMapper {
	@Insert("INSERT INTO bp_resume(user_id, filepath) VALUES (#{userId}, #{filepath})")
	int insertResume(@Param("userId") String userId, @Param("filepath") String filepath);
	
	@Select("select filepath,updated_at from bp_resume where user_id=#{userId}")
	List<ResumeInfo> selectResumesByUserId(String userId);
	
	@Delete("DELETE FROM bp_resume WHERE user_id = #{userId} AND filepath = #{filepath}")
	int deleteResume(@Param("userId") String userId,@Param("filepath") String filePath);
}
