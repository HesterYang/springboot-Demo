package com.mail.springbootmail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TemplateEngine templateEngine;

	@Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("18737876616@163.com", "simpleMailTest", "hello word");
	}
	
	@Test
	public void testHtmlMail() throws Exception {
		String content = "<html>\n" +
				"<body>\n" +
				"	<h3>hello word,这是一封html邮件</h3>\n" +
				"</body>\n" +
				"</html>";
		mailService.sendHtmlMail("18737876616@163.com", "htmlMailTest", content);
	}
	
	@Test
	public void sendAttachmentMail() {
		String filePath = "C:\\Users\\Hester\\Desktop\\日报.txt";
		
		mailService.sendAttachmentMail("18737876616@163.com", "带附件的邮件", "有附件，请查收！", filePath);
	}
	
	@Test
	public void sendInlineResourceMail() {
		String rscId = "hahha001";
		String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId +"\'></body></html>";
		String rscPath = "C:\\Users\\Hester\\Desktop\\pic1.jpg";
		
		mailService.sendInlineResourceMail("18737876616@163.com", "嵌有静态资源的邮件", content, rscPath, rscId);
	}
	
	@Test
	public void sendTemplateMail() {
		//创建邮件正文
		Context context = new Context();
		context.setVariable("id", "006");
		String emailContent = templateEngine.process("emailTemplate", context);
		
		mailService.sendHtmlMail("18737876616@163.com", "验证邮箱", emailContent);
	}
}
