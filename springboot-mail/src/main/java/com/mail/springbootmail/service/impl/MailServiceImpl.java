package com.mail.springbootmail.service.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.mail.springbootmail.service.MailService;

@Component
public class MailServiceImpl implements MailService{
	
	private final Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.fromMail.addr}")
	private String from;
	
	@Override
	public void sendSimpleMail(String to,String subject,String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			logger.info("简单邮件已发送");
		} catch (Exception e) {
			logger.error("简单邮件发送失败",e);
		}
	}
	
	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			mailSender.send(message);
			logger.info("html邮件发送成功");
		} catch (Exception e) {
			logger.error("html邮件发送异常", e);
		}
	}

	@Override
	public void sendAttachmentMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setFrom(from);
			helper.setSubject(subject);
			helper.setText(content, true);
			
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			
			mailSender.send(message);
			logger.info("带附件的邮件发送成功");
		}catch (Exception e) {
			logger.error("带附件的邮件发送失败",e);
		}
	}

	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
			
			mailSender.send(message);
			logger.info("带图片的邮件发送成功(嵌有静态资源的邮件已经发送)");
		}catch (MessagingException e) {
			logger.error("带图片的邮件发送异常(嵌有静态资源的邮件发送异常)",e);
		}
	}
}
