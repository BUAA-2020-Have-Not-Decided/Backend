package cn.edu.buaa.scholarshipserver.utils;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Random;

@Service
public class DigestUtil {
    /*根据email获得一个随机的MD5码*/
    public String getRandMD5Code(String email){
        Random rd = new Random();
        int rand = rd.nextInt();
        String randMD5 = DigestUtils.md5DigestAsHex(String.valueOf(rand).getBytes());//随机数的MD5
        String emailMD5 = DigestUtils.md5DigestAsHex(email.getBytes());
        StringBuilder result = DigestUtils.appendMd5DigestAsHex(randMD5.getBytes(), new StringBuilder(emailMD5));
        return result.toString();
    }
    /*根据邮箱获得一个稳定的MD5码*/
    public String getStableMD5Code(String email){
        return DigestUtils.md5DigestAsHex(email.getBytes());
    }
}
