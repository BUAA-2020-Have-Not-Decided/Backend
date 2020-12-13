package cn.edu.buaa.scholarshipserver.services.users;

import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper user_mapper;

    @Autowired
    private RedisUtil redis_util;

    @Override
    public void register(String name, String password, String email, String code) {
        User u = new User(name, password, email);
        //User u = new User();
        redis_util.setUserAndCode(u, code);
    }

    @Override
    public int verify(String code) {
        User u = redis_util.getUserByString(code);
        if(u==null)return 0;
        if(emailUsed(u.getEmail()))return 1;
        else if(usernameUsed(u.getName()))return -1;
        else{
            user_mapper.insertNewUser(u);
            redis_util.removeUserByKey(code);
            return 2;
        }
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
