package cn.edu.buaa.scholarshipserver.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

@Service
public class EmailSender {
    @Resource
    JavaMailSenderImpl JavaMailSender;
    public void sendEmail(String receiver, String code)throws MessagingException {
        MimeMessage message = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String text = String.format("请点击右侧链接注册：%s", "http://localhost:8086/user/verify/"+code);
        JavaMailSender.send(message);
    }
}
