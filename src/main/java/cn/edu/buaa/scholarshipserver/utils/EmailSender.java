package cn.edu.buaa.scholarshipserver.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailSender {
    @Resource
    JavaMailSenderImpl JavaMailSender;
    public void sendEmail(String receiver, String code)throws MessagingException {
        MimeMessage message = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String text = "请点击以下链接验证邮箱\n"+
                "http://localhost:8080/#/user/verify/"+code+"\n"
                +"激活链接将在"+df.format(new Date(new Date().getTime()+(long)10*60*1000))+"失效";
        helper.setFrom("notdecidedyet@126.com");
        helper.setTo(receiver);
        helper.setSubject("Register Confirm from Not Decided Yet");
        helper.setText(text);
        JavaMailSender.send(message);
    }
}
