package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.dao.ProjectDao;
import cn.edu.buaa.scholarshipserver.dao.ScholarDao;
import cn.edu.buaa.scholarshipserver.es.Scholar;
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
        private ScholarDao scholarDao;
        @PutMapping("/test1")
        public ResponseEntity<Response> test(){
                Scholar scholar = new Scholar();
                scholar.setScholarId(1L);
                scholar.setEnglishName("yuan cangzhou");
                scholar.setName("原仓周");
                scholar.setTitle("副教授");
                scholar.setOrganization("BUAA");
                scholar.setFans(0);
                System.out.println(scholar);
                scholarDao.save(scholar);
                return ResponseEntity.ok(new Response("ok"));
        }
        @GetMapping("/test1")
        public ResponseEntity<Response> test1(){

                return ResponseEntity.ok(new Response(scholarDao.findAll()));
        }
}
