package com.kr.setsu.service.serviceImpl;

import java.security.InvalidAlgorithmParameterException;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.List;

import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.kr.setsu.entity.PublicKeyEntity;
import com.kr.setsu.entity.UserInfo;
import com.kr.setsu.filter.RSAFilter;
import com.kr.setsu.mapper.UserMapper;
import com.kr.setsu.service.UserService;
import com.kr.setsu.service.ex.PasswordNotMatchException;
import com.kr.setsu.service.ex.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private ApplicationContext applicationContext;
	
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<UserInfo> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public UserInfo getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public Integer getUserCount() {
		return userMapper.getUserCount();
	}

	@Override
	public Integer addUser(UserInfo user) {
		return userMapper.addUser(user);
	}

	@Override
	public Integer updateUser(UserInfo user) {
		return userMapper.updateUser(user);
	}

	@Override
	public Integer deleteUser(String userId) {
		return userMapper.deleteUser(userId);
	}

	@Override
	public UserInfo login(UserInfo user) throws UserNotFoundException,PasswordNotMatchException{
		String userId = user.getUserId();
		UserInfo responseUser = userMapper.getUserInfo(userId);
		logger.info("User message in database:"+responseUser);
		if(responseUser==null)	throw new UserNotFoundException();
		String password = responseUser.getPassword();
		if(password==null) throw new PasswordNotMatchException();
		if(password.equals(user.getPassword())) {
			return responseUser;
		} else {
			throw new PasswordNotMatchException();
		}
	}
	
	public boolean changePassword(UserInfo user){
		String userId = user.getUserId();
		String password = userMapper.getPasswordById(userId);
		if(password.equals(user.getPassword())) {
			user.setPassword(user.getOldPassword());
			Integer isUpdate = updateUser(user);
				if(isUpdate > 0){
					return true;
				}else{
					return false;
				}
		} else {
			return false;
		}
	}
	
	@Override
	public String getValidCode() {

		
		return null;
	}



	@Override
	public PublicKeyEntity generatePublicKeyAndPrivateKey(String clientPublicKey){
		
		
		RSAFilter f = (RSAFilter) this.applicationContext.getBean("RSAFilter");
	    //System.out.println(f.allKeys.size());
		// TODO Auto-generated method stub
		PublicKeyEntity pke = new PublicKeyEntity();
		pke.clientPublicKey = clientPublicKey;



	        try {
	        	KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
			    kg.initialize(2048);
		        KeyPair keyPair = kg.generateKeyPair();
		        KeyFactory factoty = KeyFactory.getInstance("RSA");
	        	RSAPublicKeySpec publicKeySpec = factoty.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
		        RSAPrivateKeySpec privateKeySpec = factoty.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);
		        PublicKey publicKey = factoty.generatePublic(publicKeySpec);
		        PrivateKey privateKey = factoty.generatePrivate(privateKeySpec);
		        pke.serverPublicKey =  Base64.getEncoder().encodeToString(publicKey.getEncoded());
		        pke.serverPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

		        f.allKeys.put(clientPublicKey, pke);
		        f.keyPairs.put(clientPublicKey,privateKey);
		        
				PublicKeyEntity hsElem = new PublicKeyEntity();
			    hsElem.clientPublicKey = pke.clientPublicKey;
			    hsElem.serverPublicKey = pke.serverPublicKey;
		        logger.info("A publicKey has been generated:");
		        logger.info(pke.serverPublicKey);
		        logger.info("A privateKey has been generated:");
		        logger.info(pke.serverPrivateKey);
		        return hsElem;
			} catch (Exception e) {
				logger.error("生成公私钥失败！",e);
				e.printStackTrace();
				// TODO: handle exception
			}
			return pke;

		
	};

}
