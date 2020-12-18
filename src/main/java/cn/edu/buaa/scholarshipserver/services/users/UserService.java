package cn.edu.buaa.scholarshipserver.services.users;

import org.springframework.stereotype.Service;

public interface UserService {
    /*注册一个用户：把这个用户放到redis里面，主键是验证的链接*/
    void register(String name, String password, String email, String code);
    /*根据code，把用户从redis放到数据库中，注意先检查一下有没有人抢注，从redis中删除掉这个用户，并且把用户加入mysql中*/
    /*返回一个状态码，如果是0就是验证码有误，如果是1就是邮箱已存在，使用邮箱登录，如果是-1就是用户名被抢注，2就是成功*/
    int verify(String code);
    /*判断一个用户名有没有被用过*/
    boolean usernameUsed(String username);
    /*判断一个邮箱有没有被用过*/
    boolean emailUsed(String email);
    /*生成一个随机的密码*/
    String randomPassword();
}
