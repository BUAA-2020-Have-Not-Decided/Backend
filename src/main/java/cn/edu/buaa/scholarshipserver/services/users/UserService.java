package cn.edu.buaa.scholarshipserver.services.users;

import org.springframework.stereotype.Service;

public interface UserService {
    /*注册一个用户：把这个用户放到redis里面，主键是验证的链接*/
    void register(String name, String password, String email);
    /*根据code，把用户从redis放到数据库中，注意先检查一下有没有人抢注*/
    void verify(String code);
    /*判断一个用户名有没有被用过*/
    boolean usernameUsed(String username);
    /*判断一个邮箱有没有被用过*/
    boolean emailUsed(String email);
}
