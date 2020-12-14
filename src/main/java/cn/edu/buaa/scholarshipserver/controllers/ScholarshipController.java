package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.Paper;
import cn.edu.buaa.scholarshipserver.es.Patent;
import cn.edu.buaa.scholarshipserver.services.scholarship.PaperService;
import cn.edu.buaa.scholarshipserver.services.scholarship.PatentService;
import cn.edu.buaa.scholarshipserver.services.scholarship.ProjectService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/scholarship")
@Api(tags = {"学术成果相关接口"})
public class ScholarshipController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private PatentService patentService;

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

    @GetMapping("/advancedSearchProject")
    @ApiOperation(notes = "通过关键词查找项目(高级检索)", value = "通过关键词查找项目(高级检索)")
    public ResponseEntity<Response> advancedSearchProject(@RequestParam("organizationKeyword") String organizationKeyword,
                                                          @RequestParam("authorKeyword") String authorKeyword,
                                                          @RequestParam("journalKeyword") String journalKeyword,
                                                          @RequestParam("chineseTitleKeyword")String chineseTitleKeyword,
                                                          @RequestParam("startYear")String startYear,
                                                          @RequestParam("endYear")String endYear,
                                                          @RequestParam("page")String page,
                                                          @RequestParam("size")String size) {
        return projectService.advancedSearchProject(organizationKeyword, authorKeyword,
                journalKeyword,chineseTitleKeyword, startYear,endYear, page, size);
    }

    @GetMapping("/advancedSearchProjectSortByYear")
    @ApiOperation(notes = "通过关键词查找项目(高级检索)", value = "通过关键词查找项目(高级检索)")
    public ResponseEntity<Response> advancedSearchProjectSortByYear(@RequestParam("organizationKeyword") String organizationKeyword,
                                                          @RequestParam("authorKeyword") String authorKeyword,
                                                          @RequestParam("journalKeyword") String journalKeyword,
                                                          @RequestParam("chineseTitleKeyword")String chineseTitleKeyword,
                                                          @RequestParam("startYear")String startYear,
                                                          @RequestParam("endYear")String endYear,
                                                          @RequestParam("page")String page,
                                                          @RequestParam("size")String size) {
        return projectService.advancedSearchProjectSortByYear(organizationKeyword, authorKeyword,
                journalKeyword,chineseTitleKeyword, startYear,endYear, page, size);
    }


    @GetMapping("/getPaperByPaperId/{paperId}")
    @ApiOperation(notes = "查看文献内容", value = "查看文献内容")
    public ResponseEntity<Response> getPaperByPaperId(@PathVariable("paperId") String paperId) {
        Map<String, Object> responseMap = new TreeMap<>();
        CorrectPaper correctPaper = paperService.getPaperByPaperId(paperId);
        responseMap.put("paper",correctPaper);
        return ResponseEntity.ok(new Response(responseMap));

    }

    @GetMapping("/getPatentByPatentId/{patentId}")
    @ApiOperation(notes = "查看专利内容", value = "查看专利内容")
    public ResponseEntity<Response> getPatentByPatentId(@PathVariable("patentId") String patentId) {
        Map<String, Object> responseMap = new TreeMap<>();
        Patent patent = patentService.getPatentByPatentId(patentId);
        responseMap.put("patent",patent);
        return ResponseEntity.ok(new Response(responseMap));

    }

}
