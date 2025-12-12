package com.kr.setsu.entity;

public class PublicKeyEntity {
	public String clientPublicKey; 
	public String serverPublicKey;
	public String serverPrivateKey;
	@Override
	public String toString() {
		return "PublicKeyEntity [clientPublicKey=\n" + clientPublicKey + ", serverPublicKey=\n" + serverPublicKey
				+ ", serverPrivateKey=\n" + serverPrivateKey + "]";
	}
}
