package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.services.ScholarshipService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scholarships")
@Api(tags = {"学术成果相关接口"})
public class ScholarshipController {

    ScholarshipService scholarshipService;

    ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @GetMapping("/hello")
    @ApiOperation("一个示例接口，可通过此查看响应体格式，所有身份都可以调用")
    public ResponseEntity<Response> hello() {
        return scholarshipService.getHello();
    }

}
