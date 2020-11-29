package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.services.UserService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = {"用户相关接口"})
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id")
    public ResponseEntity<Response> getUserById(Integer id) {
        return userService.getUserById(id);
    }
}
