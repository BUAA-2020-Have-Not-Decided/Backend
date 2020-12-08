package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.users.UserService;
import cn.edu.buaa.scholarshipserver.utils.DigestUtil;
import cn.edu.buaa.scholarshipserver.utils.EmailSender;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/user")
@Api(tags = {"用户相关接口"})
public class UserController {
    @Autowired
    private UserService user_service;

    @Autowired
    private EmailSender email_sender;

    @Autowired
    private DigestUtil digest_util;

    //判断这个用户名用过没有
    @PostMapping("/nameUsed")
    public Map<String, Object> isNameUsed(@RequestParam("Username") String username){
        Map<String,Object> result = new TreeMap<>();
        boolean used = user_service.usernameUsed(username);
        result.put("Used", used);
        return result;
    }

    //判断这个邮箱用过没有
    @PostMapping("/emailUsed")
    public Map<String, Object> isEmailUsed(@RequestParam("Email") String email){
        Map<String,Object> result = new TreeMap<>();
        boolean used = user_service.emailUsed(email);
        result.put("Used", used);
        return result;
    }

    //注册：先接收前端发上来的东西，发送验证邮箱，然后把邮箱中的code保存在redis中，等待验证
    //返回一个验证链接已发至xxx邮箱
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("Name") String username, @RequestParam("Email") String email, @RequestParam("Password") String password){
        Map<String, Object> result = new TreeMap<>();
        if(user_service.emailUsed(email)){
            result.put("Message", "这个邮箱已经被用过了");
        }
        else if(user_service.usernameUsed(username)){
            result.put("Message", "这个用户名已经被用过了");
        }
        else{
            try{
                String code = digest_util.getRandMD5Code(email);
                email_sender.sendEmail(email, code);
                user_service.register(username, password, email, code);
                result.put("Message", String.format("验证链接已发送到%s，链接10分钟内有效", email));
                result.put("Status",true);
                return result;
            }
            catch(Exception e){
                result.put("Message", "邮件发送失败");
                result.put("Status", false);
                return result;
            }
        }
        result.put("Status", false);
        return result;
    }
    //用户验证的地方
    @PostMapping("/verify")
    public Map<String, Object>verifyUser(@RequestParam("Code") String code){
        Map<String, Object> result = new TreeMap<>();
        int verify_status = user_service.verify(code);
        result.put("Status", verify_status > 0);
        switch(verify_status){
            case -1:
                result.put("Message", "用户名被人抢注了");
                break;
            case 0:
                result.put("Message", "验证链接有误");
                break;
            case 1:
                result.put("Message", "邮箱已注册，请直接登录");
                break;
            case 2:
                result.put("Message", "验证成功");
                break;
            default:
                result.put("Message", "未知错误");
                break;
        }
        return result;
    }
}
