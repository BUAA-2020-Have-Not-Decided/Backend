package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.dao.ProjectDao;
import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.services.UploadService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TestController {
        @Autowired
        private UploadService uploadService;
        @PostMapping("/test1")
        public ResponseEntity<Response> UploadImage(@RequestBody String base64Data){
                return uploadService.UploadImage(base64Data);
        }
}
