package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.services.ScholarshipService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "ScholarshipController")
public class ScholarshipController {

    ScholarshipService scholarshipService;

    ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @GetMapping("/hello")
    @ApiOperation(notes = "hello!", value = "一个示例接口，可通过此查看响应体格式")
    public ResponseEntity<Response> hello() {
        return scholarshipService.getHello();
    }

}
