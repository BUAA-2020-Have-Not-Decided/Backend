package cn.edu.buaa.scholarshipserver.services.users;

import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUserDemo {

    private UserMapper userMapper;

    GetUserDemo(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ResponseEntity<Response> getUser(Integer id) {
        System.out.println(id);
        User user;
        if ((user = userMapper.selectByPrimaryKey(id)) == null)
            user = new User();
        Response response = new Response(user);
        return ResponseEntity.ok(response);
    }
}
