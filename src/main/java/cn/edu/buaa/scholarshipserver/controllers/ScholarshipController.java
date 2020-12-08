package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.services.scholarship.ProjectService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scholarship")
@Api(tags = {"学术成果相关接口"})
public class ScholarshipController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/getProjectById/{projectId}")
    @ApiOperation(notes = "查看项目内容", value = "查看项目内容")
    public ResponseEntity<Response> getProjectById(@PathVariable("projectId") String projectId) {
        return projectService.getProjectById(projectId);
    }

    @GetMapping("/getProjectListByKeyword/{keyword}/{page}/{size}")
    @ApiOperation(notes = "通过关键词查找项目", value = "通过关键词查找项目")
    public ResponseEntity<Response> getProjectListByKeyword(@PathVariable("keyword") String keyword,
                                                            @PathVariable("page") String page,
                                                            @PathVariable("size") String size) {
        return projectService.getProjectListByKeyword(keyword,page,size);
    }

}
