package com.kr.setsu.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.kr.setsu.entity.SafePasswordAndSalt;


/*
 * Author: Muradil, Date: 2024/02/15
 * 
 * This utility class is designed to generate a salt value and encrypt passwords with the generated salt.
 * Both the salt value and the encrypted password need to be stored in the database. 
 * The purpose is to safeguard user privacy and prevent unauthorized access to user passwords in the event of a database breach or unauthorized access by database operators.
 * 
 * 
 * このユーティリティクラスは、ソルト値を生成し、生成されたソルトでパスワードを暗号化するために設計されています。
 * データベースにはソルト値と暗号化されたパスワードの両方を保存する必要があります。
 * これはユーザーのプライバシーを保護し、データベースが侵害されるか、データベースのオペレーターがユーザーのパスワードを直接知ることを防ぐためのものです。
 * */

@Component
public class SafePasswordGenerator {
	private int length = 32;
	
	public  String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[length / 2];
        random.nextBytes(salt);
        return new BigInteger(1, salt).toString(16);
	}
	
	public SafePasswordAndSalt generateSafePassword(String password) {
		SafePasswordAndSalt ps = new SafePasswordAndSalt();
		ps.salt = this.generateSalt();	// 
		StringBuilder sb = new StringBuilder();	//这里无线程安全问题，因此使用StringBuilder。
		sb.append(password);
		sb.append(ps.salt);
		ps.safePassword = this.encryptUseMD5(sb.toString());
		
		return ps;
	}
	
	public String encryptUseMD5(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());

	        // Convert the byte array to a hexadecimal string
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : messageDigest) {
	            String hex = Integer.toHexString(0xFF & b);
	            if (hex.length() == 1) {
	                hexString.append('0');
	            }
	            hexString.append(hex);
	        }
	        
	        return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("password encrypt failed!");
			e.printStackTrace();
		}
		
        return null;
	}
	
	
}
