package cn.edu.buaa.scholarshipserver.utils;

import org.springframework.beans.factory.annotation.Value;
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
    @Value("${myserver.address}")
    String address;
    @Resource
    JavaMailSenderImpl JavaMailSender;
    public void sendEmail(String msg, String receiver, String suffix, String code)throws MessagingException {
        MimeMessage message = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String text = msg+"\n"+
                address+suffix+code+"\n"
                +"激活链接将在"+df.format(new Date(new Date().getTime()+(long)10*60*1000))+"失效";
        helper.setFrom("notdecidedyet@126.com");
        helper.setTo(receiver);
        helper.setSubject("Register Confirm from Not Decided Yet");
        helper.setText(text);
        JavaMailSender.send(message);
    }
}
