package cn.edu.buaa.scholarshipserver.services.users;

import org.springframework.stereotype.Service;

public interface UserService {
    /*注册一个用户*/
    void register(String name, String password, String email);
    /*验证一个用户，注意把用户存进去之前看一眼这个用户的邮箱有没有被人抢注*/
    void verify(String code);
    /*判断一个用户名有没有被用过*/
    boolean usernameUsed(String username);
    /*判断一个邮箱有没有被用过*/
    boolean emailUsed(String email);
}
