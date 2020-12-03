package cn.edu.buaa.scholarshipserver.services.users;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /*注册一个用户*/
    void register(String name, String password, String email);
    /*验证一个用户，今天晚点再写*/
    void verify(String code);
}
