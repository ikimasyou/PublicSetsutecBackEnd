package com.kr.setsu.controller;

import java.util.List;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kr.setsu.entity.AttendanceInfo;
import com.kr.setsu.entity.PublicKeyEntity;
import com.kr.setsu.entity.UserInfo;

import com.kr.setsu.filter.RSAFilter;
import com.kr.setsu.service.UserService;
import com.kr.setsu.service.ex.PasswordNotMatchException;
import com.kr.setsu.service.ex.UserNotFoundException;
import com.kr.setsu.util.HttpStatus;
import com.kr.setsu.util.JsonResult;

import java.security.SecureRandom;
import java.math.BigInteger;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
//	@Autowired
//	@Qualifier("RSAFilter")
//	private Filter singletonRSAFilter;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	JsonResult<UserInfo> login(@RequestBody UserInfo user) {
		logger.info("用户请求登录："+user);
		UserInfo userInfo = new UserInfo();
		try {
			userInfo = userService.login(user);
		}
		catch(UserNotFoundException userNotFoundException){
			return new JsonResult<UserInfo>(HttpStatus.USER_NOT_EXIST);
		}
		catch(PasswordNotMatchException passwordNotMatchException) {
			return new JsonResult<UserInfo>(HttpStatus.PASSWORD_ERROR);
		}
		
		JsonResult<UserInfo> jr = new JsonResult<UserInfo>(HttpStatus.OK);
		jr.setData(userInfo);
		return jr;
	}
	
	
	@RequestMapping(value = "/getValidCode",method = RequestMethod.POST)
	@ResponseBody
	JsonResult<String> getValidCode(){
		
		
        this.userService.getValidCode();
		return null;
	}
	
	
	@RequestMapping(value = "/exchange",method = RequestMethod.POST)
	@ResponseBody
	JsonResult<PublicKeyEntity> keyExchange(@RequestBody PublicKeyEntity pke) {
		

	    PublicKeyEntity pe = this.userService.generatePublicKeyAndPrivateKey(pke.clientPublicKey);
	    //System.out.println(pe);
	    
	    
	    PublicKeyEntity result = new PublicKeyEntity();
	    result.serverPublicKey = pe.serverPublicKey;
		//System.out.println(result);
		JsonResult<PublicKeyEntity> jr = new JsonResult<>(HttpStatus.OK);
		jr.setData(result);

		return jr;
	}
	
	@RequestMapping(value = "/changePassword",method = RequestMethod.POST)
	@ResponseBody
	boolean changePassword(@RequestBody UserInfo user) {
		if(userService.changePassword(user)) {
			return true;
		}else {
			return false;
		}
		
	}


//	@RequestMapping("/login")
//	@ResponseBody
//	boolean login(UserInfo user) {
//		return userService.login(user);
//	}

}
