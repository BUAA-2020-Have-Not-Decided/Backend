package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.dao.*;
import cn.edu.buaa.scholarshipserver.es.Cooperation;
import cn.edu.buaa.scholarshipserver.es.DataScholar;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.services.UploadService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class TestController {
        @Autowired
        private ScholarDao scholarDao;
        @Autowired
        private PaperDao paperDao;
        @Autowired
        private ProjectDao projectDao;
        @Autowired
        private PatentDao patentDao;
        @Autowired
        private WorkExperienceDao workExperienceDao;
        @Autowired
        private InstitutionDao institutionDao;
        @Autowired
        private DataScholarDao dataScholarDao;
        @Autowired
        private CooperationDao cooperationDao;
        @Autowired
        private PaperDataScholarDao paperDataScholarDao;
        @PutMapping("/test1")
        public ResponseEntity<Response> test(){
                List<DataScholar> dataScholar = dataScholarDao.findByScholarId(13);
                for(int i = 0 ;i<dataScholar.size();i++){
                        dataScholar.get(i).setScholarId(-1);
                }
                dataScholarDao.saveAll(dataScholar);
                return ResponseEntity.ok(new Response(dataScholar));
        }
        @GetMapping("/test1")
        public ResponseEntity<Response> test1(){
                return ResponseEntity.ok(new Response(paperDataScholarDao.findByAuthorId(2099217884L)));
        }
        @GetMapping("/test2")
        public ResponseEntity<Response> test2(){



                return ResponseEntity.ok(new Response(scholarDao.findByName("路路路")));

        }
        @GetMapping("/test3")
        public ResponseEntity<Response> test3(){
                return ResponseEntity.ok(new Response(patentDao.findById(1L)));
        }
        @GetMapping("/test4/{Id}")
        public ResponseEntity<Response> test4(@PathVariable("Id") String id){
                List<List<Cooperation>> list1 = new ArrayList<>();
                list1.add(cooperationDao.findByAuthorId1(Long.valueOf(id)));
                list1.add(cooperationDao.findByAuthorId2(Long.valueOf(id)));
                return ResponseEntity.ok(new Response(list1));
        }
}
