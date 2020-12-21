package cn.edu.buaa.scholarshipserver.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailSender {
    @Value("${myserver.address}")
    String address;
    @Resource
    JavaMailSenderImpl JavaMailSender;
    public void sendEmail(String msg, String receiver, String suffix, String code, String title) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String text = msg+"\n"+
                address+suffix+code+"\n"
                +"激活链接将在"+df.format(new Date(new Date().getTime()+(long)10*60*1000))+"失效";
        helper.setFrom("notdecidedyet@126.com","NOsearch项目组");
        helper.setTo(receiver);
        helper.setSubject(title);
        helper.setText(text);
        JavaMailSender.send(message);
    }

    public void sendPasswordEmail(String receiver, String password) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        String text = "您好，您在NOsearch平台的登录密码是："+password;
        helper.setFrom("notdecidedyet@126.com", "NOsearch项目组");
        helper.setTo(receiver);
        helper.setSubject("NOsearch找回密码");
        helper.setText(text);
        JavaMailSender.send(message);
    }
}
