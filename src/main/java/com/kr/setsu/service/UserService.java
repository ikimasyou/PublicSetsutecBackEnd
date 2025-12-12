package com.kr.setsu.service;

import java.util.List;

import com.kr.setsu.entity.PublicKeyEntity;
import com.kr.setsu.entity.UserInfo;

public interface UserService {

	List<UserInfo> getUserList();

    UserInfo getUserById(String id);

    Integer getUserCount();

    Integer addUser(UserInfo user);
	
	Integer updateUser(UserInfo user);
	
	Integer deleteUser(String id);
	
	UserInfo login(UserInfo user);
	
	PublicKeyEntity generatePublicKeyAndPrivateKey(String clientPublicKey);
	
	boolean changePassword(UserInfo user);

	String getValidCode();


}
