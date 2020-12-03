package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.users.UserService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/user")
@Api(tags = {"用户相关接口"})
public class UserController {
    @Autowired
    private UserService user_service;

    /*判断这个用户名用过没有*/
    @PostMapping("/nameUsed")
    public Map<String, Object> isNameUsed(@RequestParam("Username") String username){
        Map<String,Object> result = new TreeMap<>();
        boolean used = user_service.usernameUsed(username);
        result.put("Used", used);
        return result;
    }

    /*判断这个邮箱用过没有*/
    @PostMapping("/emailUsed")
    public Map<String, Object> isEmailUsed(@RequestParam("Email") String email){
        Map<String,Object> result = new TreeMap<>();
        boolean used = user_service.emailUsed(email);
        result.put("Used", used);
        return result;
    }

    /*注册：先接收前端发上来的东西，发送验证邮箱，然后把邮箱中的code保存在redis中，等待验证*/
    /*返回一个验证链接已发至xxx邮箱*/
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("Name") String username, @RequestParam("Email") String email, @RequestParam("Password") String password){
        Map<String, Object> result = new TreeMap<>();
        user_service.register(username, password, email);
        if(user_service.emailUsed(email)){
            result.put("message", "这个邮箱已经被用过了");
        }
        else if(user_service.usernameUsed(username)){
            result.put("message", "这个用户名已经被用过了");
        }
        else{
            result.put("message", String.format("验证链接已发至%s，链接一天内有效", email));
            result.put("status", true);
            return result;
        }

        result.put("success", false);
        return result;
    }

}
