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

import java.util.HashMap;
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
        return projectService.getProjectListByKeyword(keyword, page, size);
    }

    @GetMapping("/advancedSearchProject")
    @ApiOperation(notes = "高级检索项目（相关度排序）", value = "高级检索项目（相关度排序）")
    public ResponseEntity<Response> advancedSearchProject(@RequestParam("titleKW") String titleKW,
                                                          @RequestParam("abstractKW") String abstractKW,
                                                          @RequestParam("organizationKW") String organizationKW,
                                                          @RequestParam("authorKW") String authorKW,
                                                          @RequestParam("startDate") String startDate,
                                                          @RequestParam("endDate") String endDate,
                                                          @RequestParam("page") String page,
                                                          @RequestParam("size") String size) {
        String organizationKeyword = organizationKW;
        String authorKeyword = authorKW;
        String journalKeyword = "";
        String chineseTitleKeyword = titleKW;
        String startYear = "";
        String endYear = "";
        if (!startDate.equals(""))
            startYear = startDate.substring(0, 4);
        if (!endDate.equals(""))
            endYear = endDate.substring(0, 4);
        //需要保证data格式为yyyy
        return projectService.advancedSearchProject(organizationKeyword, authorKeyword,
                journalKeyword, chineseTitleKeyword, startYear, endYear, page, size);
    }

    @GetMapping("/advancedSearchProjectSortByDate")
    @ApiOperation(notes = "高级检索项目（日期排序）", value = "高级检索项目（日期排序）")
    public ResponseEntity<Response> advancedSearchProjectSortByDate(@RequestParam("titleKW") String titleKW,
                                                                    @RequestParam("abstractKW") String abstractKW,
                                                                    @RequestParam("organizationKW") String organizationKW,
                                                                    @RequestParam("authorKW") String authorKW,
                                                                    @RequestParam("startDate") String startDate,
                                                                    @RequestParam("endDate") String endDate,
                                                                    @RequestParam("page") String page,
                                                                    @RequestParam("size") String size) {
        String organizationKeyword = organizationKW;
        String authorKeyword = authorKW;
        String journalKeyword = "";
        String chineseTitleKeyword = titleKW;
        String startYear = "";
        String endYear = "";
        if (!startDate.equals(""))
            startYear = startDate.substring(0, 4);
        if (!endDate.equals(""))
            endYear = endDate.substring(0, 4);
        //需要保证data格式为yyyy
        return projectService.advancedSearchProjectSortByYear(organizationKeyword, authorKeyword,
                journalKeyword, chineseTitleKeyword, startYear, endYear, page, size);
    }

    @GetMapping("/getPaperByPaperId/{paperId}")
    @ApiOperation(notes = "查看文献内容", value = "查看文献内容")
    public ResponseEntity<Response> getPaperByPaperId(@PathVariable("paperId") String paperId) {
        Map<String, Object> responseMap = new TreeMap<>();
        CorrectPaper correctPaper = paperService.getPaperByPaperId(paperId);
        responseMap.put("paper", correctPaper);
        return ResponseEntity.ok(new Response(responseMap));
    }

    @GetMapping("/getPatentByPatentId/{patentId}")
    @ApiOperation(notes = "查看专利内容", value = "查看专利内容")
    public ResponseEntity<Response> getPatentByPatentId(@PathVariable("patentId") String patentId) {
        Map<String, Object> responseMap = new TreeMap<>();
        Patent patent = patentService.getPatentByPatentId(patentId);
        responseMap.put("patent", patent);
        return ResponseEntity.ok(new Response(responseMap));

    }

    @GetMapping("/advancedSearchPaper")
    @ApiOperation(notes = "高级检索文献（相关度排序）", value = "高级检索文献（相关度排序")
    public ResponseEntity<Response> advancedSearchPaper(@RequestParam("titleKW") String titleKW,
                                                        @RequestParam("abstractKW") String abstractKW,
                                                        @RequestParam("doctype") int doctype,
                                                        @RequestParam("organizationKW") String organizationKW,
                                                        @RequestParam("authorKW") String authorKW,
                                                        @RequestParam("startDate") String startDate,
                                                        @RequestParam("endDate") String endDate,
                                                        @RequestParam("page") String page,
                                                        @RequestParam("size") String size) {
        if (doctype < 0 || doctype > 2) {
            return ResponseEntity.ok(new Response(400, "文献类型不正确", 400));
        }
        String yyyy;
        String MM;
        String dd;
        if (!startDate.equals("")) {
            yyyy = startDate.substring(0, 4);
            MM = startDate.substring(4, 6);
            dd = startDate.substring(6, 8);
            startDate = yyyy + '-' + MM + '-' + dd + "T00:00:00.0000000";
        }
        if (!endDate.equals("")) {
            yyyy = endDate.substring(0, 4);
            MM = endDate.substring(4, 6);
            dd = endDate.substring(6, 8);
            endDate = yyyy + '-' + MM + '-' + dd + "T00:00:00.0000000";
        }
        return paperService.advancedSearchPaper(titleKW, abstractKW, doctype, organizationKW, authorKW, startDate, endDate, page, size);
    }

    @GetMapping("/advancedSearchPaperSortByDate")
    @ApiOperation(notes = "高级检索文献（日期排序）", value = "高级检索文献（日期排序")
    public ResponseEntity<Response> advancedSearchPaperSortByDate(@RequestParam("titleKW") String titleKW,
                                                                  @RequestParam("abstractKW") String abstractKW,
                                                                  @RequestParam("organizationKW") String organizationKW,
                                                                  @RequestParam("authorKW") String authorKW,
                                                                  @RequestParam("startDate") String startDate,
                                                                  @RequestParam("endDate") String endDate,
                                                                  @RequestParam("page") String page,
                                                                  @RequestParam("size") String size) {
        return null;
    }

    @GetMapping("/advancedSearchPatent")
    @ApiOperation(notes = "高级检索专利（相关度排序）", value = "高级检索专利（相关度排序")
    public ResponseEntity<Response> advancedSearchPatent(@RequestParam("titleKW") String titleKW,
                                                         @RequestParam("abstractKW") String abstractKW,
                                                         @RequestParam("organizationKW") String organizationKW,
                                                         @RequestParam("authorKW") String authorKW,
                                                         @RequestParam("startDate") String startDate,
                                                         @RequestParam("endDate") String endDate,
                                                         @RequestParam("page") String page,
                                                         @RequestParam("size") String size) {
        //需要保证date格式为yyyyMMdd
        return patentService.advancedSearchPatent(titleKW, abstractKW, organizationKW, authorKW, startDate, endDate, page, size);
    }

    @GetMapping("/advancedSearchPatentSortByDate")
    @ApiOperation(notes = "高级检索专利（日期排序）", value = "高级检索专利（日期排序")
    public ResponseEntity<Response> advancedSearchPatentSortByDate(@RequestParam("titleKW") String titleKW,
                                                                   @RequestParam("abstractKW") String abstractKW,
                                                                   @RequestParam("organizationKW") String organizationKW,
                                                                   @RequestParam("authorKW") String authorKW,
                                                                   @RequestParam("startDate") String startDate,
                                                                   @RequestParam("endDate") String endDate,
                                                                   @RequestParam("page") String page,
                                                                   @RequestParam("size") String size) {

        //需要保证date格式为yyyyMMdd
        return patentService.advancedSearchPatentSortByDate(titleKW, abstractKW, organizationKW, authorKW, startDate, endDate, page, size);
    }

}
