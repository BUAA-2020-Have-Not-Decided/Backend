package cn.edu.buaa.scholarshipserver.services.users;

import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper user_mapper;

    /*注册之后，先新建一个用户的对象，放到redis里面，等待接收验证*/
    @Override
    public void register(String name, String password, String email) {
        User u = new User(name, password, email);
    }

    @Override
    public void verify(String code) {

    }

    @Override
    public boolean usernameUsed(String username) {
        User u = user_mapper.getUserByName(username);
        System.out.println(u);
        return u != null;
    }

    @Override
    public boolean emailUsed(String email) {
        User u = user_mapper.getUserByEmail(email);
        System.out.println(u);
        return u != null;
    }
}
