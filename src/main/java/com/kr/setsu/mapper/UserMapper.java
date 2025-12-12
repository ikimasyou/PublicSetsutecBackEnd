package com.kr.setsu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kr.setsu.entity.UserInfo;

@Repository
@Mapper
public interface   UserMapper {

	@Select("select * from m_user")
	@ResultMap("userInfoResult")
	List<UserInfo> getUserList();
	
	
	@Select ("select * from m_user where user_id = #{userId}")
	@ResultMap("userInfoResult")
	UserInfo getUserById(@Param("userId")String userId);
	@Select("select count(*) as cnt from m_user")
	Integer getUserCount();
	
	
	@Select("select * from m_user where user_id = #{userId}")
	@ResultMap("userInfoResult")
	UserInfo getUserInfo(@Param("userId")String userId);
	
	@Select("select * from m_user limit #{cursor},#{offset}")
	@ResultMap("userInfoResult")
	List<UserInfo>getUserPage(@Param("cursor")Integer cursor , @Param("offset")Integer offset);
	
	Integer addUser(UserInfo user);
	
	Integer updateUser(UserInfo user);
	
	@Delete("delete from m_user where user_id = #{userId}")
	Integer deleteUser(@Param("userId") String userId);
	
	@Select ("select password from m_user where user_id = #{userId}")
	String getPasswordById(@Param("userId") String userId);
}
