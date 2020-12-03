package cn.edu.buaa.scholarshipserver.services.users;

import cn.edu.buaa.scholarshipserver.models.User;

public class UserServiceImpl implements UserService{

    @Override
    public void register(String name, String password, String email) {
        User u = new User(name, password, email);
    }

    @Override
    public void verify(String code) {

    }
}
