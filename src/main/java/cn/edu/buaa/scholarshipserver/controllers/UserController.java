package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.dao.ScholarDao;
import cn.edu.buaa.scholarshipserver.dao.ScholarMapper;
import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.message.MessageService;
import cn.edu.buaa.scholarshipserver.services.users.UserService;
import cn.edu.buaa.scholarshipserver.utils.*;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @Autowired
    private ScholarMapper scholar_mapper;

    @Autowired
    private ScholarDao scholar_dao;

    @Autowired
    private MessageService message_service;

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
                email_sender.sendEmail("点击链接完成用户激活", email, "/#/user/verify/",code, "用户激活确认");
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

    //用来验证用户的地方
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

    //用来验证学者的地方
    @PostMapping("/scholar/verify")
    public Response verifyScholar(@RequestParam("Code") String code){
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        try{
            Scholar s = this.redis_util.getScholarByCode(code);
            this.scholar_mapper.insert(s);
            s = this.scholar_mapper.selectByEmail(s.getEmail());
            cn.edu.buaa.scholarshipserver.es.Scholar ss = new cn.edu.buaa.scholarshipserver.es.Scholar();
            ss.setEmail(s.getEmail());
            ss.setName(s.getName());
            ss.setEnglishName(s.getEnglishname());
            ss.setScholarId(s.getScholarid());
            this.scholar_dao.save(ss);
            int uid = s.getUid();
            this.user_mapper.updateIdentify(uid, 1);
            res.setMessage("认证学者成功");
        }catch(Exception e){
            res.setCode(500);
            res.setMessage("服务器有点问题");
        }
        return res;
    }

    //TODO 接收前端上传的头像
    @PostMapping("/avatar")
    public Response uploadAvatar(@RequestParam("Base64")String picture){
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        try{
            String url = this.message_service.uploadImage(picture);
            res.setMessage("头像上传成功");
            User current_user = (User)SecurityUtils.getSubject().getPrincipal();
            int current_uid = current_user.getUserID();
            this.user_mapper.updateImagePath(current_uid, url);
            data.put("url", url);
        }catch(Exception e){
            res.setCode(500);
            res.setMessage("图片保存失败");
        }
        return res;
    }

    //TODO 找回密码
    // 发送邮件，点击邮件重置密码
    @PostMapping("/findPassword")
    public Response findPassword(@RequestParam("Email")String email){
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        User current_user = (User)SecurityUtils.getSubject().getPrincipal();
        String new_pwd = this.user_service.randomPassword();
        System.out.println(new_pwd);
        return res;
    }

    @PostMapping("/getInfo")
    public Response giveInfo(){
        /*获取当前用户*/
        User current_user = (User)SecurityUtils.getSubject().getPrincipal();
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        data.put("email", current_user.getEmail());
        data.put("username", current_user.getName());
        data.put("uid", current_user.getUserID());
        data.put("isScholar", current_user.getIdentify()==1);
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
            data.put("username", u.getName());
            data.put("avatar", u.getUserImagePath());
            String jwt = JwtUtil.createToken(u.getEmail(), new Date().getTime());
            response.setHeader("Authorization",jwt);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            redis_util.setUserAndJWT(u, jwt);
        }
        return res;
    }

    @PostMapping("/toBeScholar")
    public Response beScholar(@RequestParam("OrgEmail")String email,@RequestParam("RealName") String real_name, @RequestParam("EnglishName")String english_name){
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        User u = (User)SecurityUtils.getSubject().getPrincipal();
        Scholar s = new Scholar(u.getUserID(), real_name, email, english_name);
        if(u.getIdentify()==1){//这个用户已经是学者了
            res.setCode(501);
            res.setMessage("已经是学者了");
            return res;
        }
        if(u.getEmail().compareTo(s.getEmail())==0){//如果学者的邮箱和用户一样，那就直接搞定
            this.scholar_mapper.insert(s);
            s = this.scholar_mapper.selectByUID(u.getUserID());
            this.user_mapper.updateIdentify(u.getUserID(), 1);
            res.setMessage("认证成功");
            res.setCode(1002);
            cn.edu.buaa.scholarshipserver.es.Scholar ss = new cn.edu.buaa.scholarshipserver.es.Scholar();
            ss.setScholarId(s.getScholarid());
            ss.setName(s.getName());
            ss.setEnglishName(s.getEnglishname());
            ss.setEmail(s.getEmail());
            System.out.println(ss);
            this.scholar_dao.save(ss);
            return res;
        }
        else if(this.user_service.emailUsed(email)){
            res.setCode(502);
            res.setMessage("这个邮箱被用过了");
            return res;
        }
        else{//如果不一样，那就发送邮件，并且把相关的东西存在redis中
            try{
                String rand_code = this.digest_util.getRandMD5Code(email);
                this.email_sender.sendEmail("点击链接完成学者认证", email, "/#/user/scholarVerify/",rand_code, "学着认证邮箱验证");
                this.redis_util.setScholarAndCode(s, rand_code);
                res.setMessage(String.format("验证邮件已发送到%s，连接在10分钟内有效", email));
                return res;
            }catch(Exception e){
                res.setMessage("邮件发送失败");
                res.setCode(500);
                return res;
            }
        }
    }

    @PostMapping("/modifyUsername")
    public Response modifyUsername(@RequestParam("NewName")String name){
        User current_user = (User)SecurityUtils.getSubject().getPrincipal();
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        if(this.user_service.usernameUsed(name)){
            res.setCode(500);
            res.setMessage("用户名重复了");
            return res;
        }
        this.user_mapper.updateName(current_user.getUserID(), name);
        data.put("success", true);
        return res;
    }

    @PostMapping("/modifyPassword")
    public Response modifyPassword(@RequestParam("NewPassword") String new_password, @RequestParam("OldPassword")String old_password){
        User current_user = (User)SecurityUtils.getSubject().getPrincipal();

        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        if(current_user.getPassword().compareTo(old_password)!=0){
            res.setCode(500);
            res.setMessage("密码有误");
            return res;
        }
        this.user_mapper.updatePassword(current_user.getUserID(), new_password);
        data.put("success", true);
        return res;
    }

    @PostMapping("/modifyEmail")
    public Response modifyEmail(@RequestParam("Email")String email){
        User current_user = (User)SecurityUtils.getSubject().getPrincipal();
        current_user.setEmail(email);
        String code = this.digest_util.getRandMD5Code(email);
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        if(this.user_service.emailUsed(email)){
            res.setMessage("这个邮箱被用过了");
            res.setCode(501);
            return res;
        }
        try{
            this.email_sender.sendEmail("点击这个链接完成邮箱修改", email, "/#/user/link/modifyEmail/", code,"修改邮箱验证");
            this.redis_util.setUserAndCode(current_user, code);
        }catch(Exception e){
            res.setMessage("邮件发送失败");
            res.setCode(500);
        }
        return res;
    }

    @PostMapping("/link/modifyEmail")
    public Response linkModifyEmail(@RequestParam("Code")String code){
        HashMap<String,Object> data = new HashMap<>();
        Response res = new Response(data);
        User modified = this.redis_util.getUserByString(code);
        this.redis_util.removeItemByKey(code);
        if(this.user_service.emailUsed(modified.getEmail())){
            res.setCode(500);
            res.setMessage("修改错误");
        }
        else{
            this.user_mapper.updateEmail(modified.getUserID(), modified.getEmail());
            res.setMessage("邮箱修改成功");
        }
        return res;

    }

    @PostMapping("/getScholarId")
    public Response getScholarIdByUserId(@RequestParam("UserID")int user_id){
        User u = this.user_mapper.getUserByID(user_id);
        HashMap<String, Object> data = new HashMap<>();
        Response res = new Response(data);
        if(u==null){
            res.setMessage("这个用户不存在");
            res.setCode(500);
        }
        else{
            if(u.getIdentify()!=1){
                res.setMessage("他/她不是学者");
                res.setCode(501);
            }
            else{
                res.setMessage("找到了这个学者");
                data.put("scholarId", this.scholar_mapper.selectByUID(user_id).getScholarid());
            }
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
    @GetMapping("/unauthorized")
    @ResponseBody
    public Response unauthorized(){
        Response res = new Response(null);
        res.setCode(-1);
        res.setMessage("Not authorized");
        return res;
    }
}
