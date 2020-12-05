package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.services.users.GetUserDemo;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private GetUserDemo getUserDemo;

    UserService(GetUserDemo getUserDemo) {
        this.getUserDemo = getUserDemo;
    }

    /*public ResponseEntity<Response> getUserById(Integer id) {
        return getUserDemo.getUser(id);
    }*/
}
