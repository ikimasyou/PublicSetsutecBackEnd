package com.kr.setsu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kr.setsu.entity.MailAccount;

@Repository
@Mapper
public interface MailMapper {

	@Select("select * from user_email_account where user_id=#{userId}")
	public MailAccount getMailAccountByUserId(String userId);
}
