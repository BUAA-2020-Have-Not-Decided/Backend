package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.services.ScholarshipService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScholarshipController {

    ScholarshipService scholarshipService;

    ScholarshipController(ScholarshipService scholarshipService) {
        this.scholarshipService = scholarshipService;
    }

    @GetMapping("/hello")
    public String hello() {
        return scholarshipService.getHello();
    }

}
