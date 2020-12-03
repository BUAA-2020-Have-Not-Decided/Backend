package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.services.ScholarService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/schlorship")
@Api(tags = {"学者门户相关接口"})
public class ScholarController {
        @Autowired
        private ScholarService scholarService;
        @GetMapping("/own/{ScholarId}")
        public ResponseEntity<Response> getScholar(@PathVariable("ScholarId") Integer ScholarId){

                return  scholarService.getScholarById(ScholarId);
        }
}
