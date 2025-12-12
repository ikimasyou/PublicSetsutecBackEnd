package com.kr.setsu.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kr.setsu.entity.MailAccount;
import com.kr.setsu.mapper.MailMapper;
import com.kr.setsu.service.MailService;
import com.kr.setsu.util.MailSendFactory;

@Service
public class MailServiceImpl implements MailService{
	
	@Value("${mail.address}")
	private String mailAddress;

	@Autowired
	private MailSendFactory mailSendFactory;
	
	@Autowired
	private MailMapper mailMapper;
	
	@Override
	public boolean mailTo(String title,String message,MailAccount mailAccount) {
		// TODO Auto-generated method 
		try {
			JavaMailSender mailSender=mailSendFactory.createMailSender(mailAccount.getEmail(), mailAccount.getEmailPassword());
			SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(mailAccount.getEmail());
            msg.setTo(mailAddress);
            msg.setSubject(title);
            msg.setText(message);
            mailSender.send(msg);
            return true;
		}catch (Exception e) {
            // 记录日志，定位错误
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public MailAccount getMailAccountByUserId(String userId) {
		// TODO Auto-generated method stub
		MailAccount mailAccount=mailMapper.getMailAccountByUserId(userId);
		return mailAccount;
	}

}
