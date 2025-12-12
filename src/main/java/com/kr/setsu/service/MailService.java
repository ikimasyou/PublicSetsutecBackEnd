package com.kr.setsu.service;

import com.kr.setsu.entity.MailAccount;

public interface MailService {

	public boolean mailTo(String title,String message,MailAccount mailAccount);
	
	public MailAccount getMailAccountByUserId(String userId);
}
