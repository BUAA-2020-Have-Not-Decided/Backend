package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.users.UserService;
import cn.edu.buaa.scholarshipserver.utils.*;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private RedisUtil redis_util;

    @Autowired
    private UserMapper user_mapper;

    //判断这个用户名用过没有
    @PostMapping("/nameUsed")
    public Response isNameUsed(@RequestParam("Username") String username){
        Map<String,Object> result = new TreeMap<>();
        Response res = new Response(result);
        boolean used = user_service.usernameUsed(username);
        if(used){
            res.setCode(400);
            res.setMessage("这个用户名已经被注册了");
        }
        else{
            res.setCode(1001);
            res.setMessage("这个用户名没被注册过");
        }
        result.put("Used", used);
        return res;
    }

    //判断这个邮箱用过没有
    @PostMapping("/emailUsed")
    public Response isEmailUsed(@RequestParam("Email") String email){
        Map<String,Object> result = new TreeMap<>();
        Response res = new Response(result);
        boolean used = user_service.emailUsed(email);
        if(used){
            res.setCode(400);
            res.setMessage("这个用户名已经被注册了");
        }
        else{
            res.setCode(1001);
            res.setMessage("这个用户名没被注册过");
        }
        result.put("Used", used);
        return res;
    }

    //注册：先接收前端发上来的东西，发送验证邮箱，然后把邮箱中的code保存在redis中，等待验证
    //返回一个验证链接已发至xxx邮箱
    @PostMapping("/register")
    public Response register(@RequestParam("Name") String username, @RequestParam("Email") String email, @RequestParam("Password") String password){
        Map<String, Object> result = new TreeMap<>();
        Response res = new Response(result);
        if(user_service.emailUsed(email)){
            res.setMessage("这个邮箱已经被使用过了");
            res.setCode(401);
        }
        else if(user_service.usernameUsed(username)){
            res.setMessage("这个用户名已经被使用过了");
            res.setCode(402);
        }
        else{
            try{
                String code = digest_util.getRandMD5Code(email);
                email_sender.sendEmail(email, code);
                user_service.register(username, password, email, code);
                res.setMessage(String.format("验证链接已发送到%s，链接10分钟内有效", email));
                res.setCode(1001);
                result.put("Status",true);
                return res;
            }
            catch(Exception e){
                res.setMessage("邮箱发送失败");
                res.setCode(400);
                result.put("Status", false);
                return res;
            }
        }
        result.put("Status", false);
        return res;
    }

    //用户验证的地方
    @PostMapping("/verify")
    public Response verifyUser(@RequestParam("Code") String code){
        Map<String, Object> result = new TreeMap<>();
        Response res = new Response(result);
        int verify_status = user_service.verify(code);
        result.put("Status", verify_status > 0);
        switch(verify_status){
            case -1:
                res.setMessage("此用户名被人抢注了，请您重新注册");
                res.setCode(401);
                break;
            case 0:
                res.setMessage("验证链接过期或错误，请重新验证");
                res.setCode(400);
                break;
            case 1:
                res.setMessage("验证已完成，请您直接登录");
                res.setCode(1001);
                break;
            case 2:
                res.setMessage("验证成功");
                res.setCode(1001);
                break;
            default:
                result.put("Message", "未知错误");
                break;
        }
        return res;
    }

    //用户登录的地方
    @PostMapping("/login")
    public Response login(@RequestParam("Email") String email, @RequestParam("Password") String password, HttpServletResponse response){
        Map<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        User u = this.user_mapper.getUserByEmail(email);
        if(u == null){//用户不存在，500
            data.put("success", false);
            res.setCode(500);
        }
        else if(u.getPassword().compareTo(password)!=0){//密码错误，501
            data.put("success",false);
            res.setCode(501);
        }
        else{//成功1001
            data.put("success", true);
            res.setCode(1001);
            data.put("identification", u.getIdentify());
            String jwt = JwtUtil.createToken(u.getEmail(), new Date().getTime());
            response.setHeader("Authorization",jwt);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            redis_util.setUserAndJWT(u, jwt);
        }
        return res;
    }

    //尝试进行jwt_user登录
    @PostMapping("/jwtLoginUserTest")
    @ResponseBody
    public String tryLogin(@RequestParam("Test")int test)
    {
        return (SecurityUtils.getSubject().getPrincipal()).toString();
    }

    //尝试进行jwt_scholar登录
    @PostMapping("/jwtLoginScholarTest")
    @ResponseBody
    public String tryLoginScholar(@RequestParam("Test")int test)
    {
        return "登陆成功";
    }

    //尝试进行jwt_scholar登录
    @PostMapping("/jwtLoginAdminTest")
    @ResponseBody
    public String tryLoginAdmin(@RequestParam("Test")int test)
    {
        return "登陆成功";
    }
    //用来显示没有权限
    @PostMapping("/unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "unauthorized!";
    }
}
