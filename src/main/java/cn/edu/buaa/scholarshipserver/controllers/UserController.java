package cn.edu.buaa.scholarshipserver.controllers;

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
}
