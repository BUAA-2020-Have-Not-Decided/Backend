package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.dao.ProjectDao;
import cn.edu.buaa.scholarshipserver.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test1")
public class TestController {
        @Autowired
        private ProjectDao projectDao;
        @GetMapping("/{id}")
        public Project getProjectById(@PathVariable String id){
                return  projectDao.findByFundProjectCode(id);
        }
}
