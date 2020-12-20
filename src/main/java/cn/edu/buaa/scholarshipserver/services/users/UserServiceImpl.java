package cn.edu.buaa.scholarshipserver.services.users;

import cn.edu.buaa.scholarshipserver.dao.ScholarMapper;
import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper user_mapper;

    @Autowired
    private RedisUtil redis_util;

    @Autowired
    private ScholarMapper scholar_mapper;

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
            redis_util.removeItemByKey(code);
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
        Scholar s = this.scholar_mapper.selectByEmail(email);
        return u != null||s!=null;
    }

    @Override
    public String randomPassword() {
        StringBuilder rand_password = new StringBuilder();
        int i;
        Random rd = new Random();
        for(i=0;i<8;i++){
            rand_password.append((char)(97+rd.nextInt()%26));
        }
        for(i=0;i<4;i++){
            rand_password.append((char)(rd.nextInt()%10));
        }
        return rand_password.toString();
    }
}
