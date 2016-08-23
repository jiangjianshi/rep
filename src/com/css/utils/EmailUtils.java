package com.css.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;
/**
 * 发邮件工具类
 * @author syw
 *
 */
public class EmailUtils {

	private static final long serialVersionUID = 0L;

	private static EmailUtils time = null;

	private final String format = "yyyy-MM-dd HH:mm:ss";

	public static synchronized EmailUtils getInstance() {
		if (time == null) {
			time = new EmailUtils();
		}
		return time;
	}
	
	/**
	 * 发送邮件
	 * @param emailTitle 
	 * @param emailContent
	 * @return
	 */
	public boolean sendEmail(String emailTitle,String emailContent,String toEmail) {
		boolean mailsuccess = true;
		try{
			String smtpserver = "smtp.163.com";//smtp服务器
			String mailfrom = "jiangjianshi001@163.com";//发送的邮箱
			String account = "jiangjianshi001";
			String password = "2009myyx0522";
			String title = emailTitle;
			String content =emailContent;
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpserver);
			props.put("mail.smtp.auth", "true");
			javax.mail.Session mailSession = javax.mail.Session.getInstance(props, null);

			MimeMessage msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(mailfrom));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(toEmail, false));//收件邮箱
			msg.setSubject(title,"GBK");
			msg.setText(content,"GBK");
			msg.setHeader("X-Mailer", "smtpsend");
			msg.setHeader("Content-Type", "text/html; charset=gb2312");
			msg.setSentDate(new java.util.Date());
			SMTPTransport t = (SMTPTransport)mailSession.getTransport("smtp");
			t.connect(smtpserver, account, password);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
		}catch(Exception e){
			mailsuccess = false;
			e.printStackTrace();
		}
		return mailsuccess;
	}
	
	public static void main(String[] args) {
		EmailUtils e = EmailUtils.getInstance();
		int count = 0;
		for(int i=1940120226;i<1999999999;i++){
			String toMail = (i+"").substring(1)+"@qq.com";
			boolean flag = e.sendEmail("hello boy","hello boy!",toMail);
			System.out.println(count+": "+toMail+"..."+flag);//测试成功
			count++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
}
