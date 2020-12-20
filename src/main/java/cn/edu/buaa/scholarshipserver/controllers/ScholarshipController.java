package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.controllers.scholarship.ChangeStarStatus;
import cn.edu.buaa.scholarshipserver.dao.ScholarDao;
import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.Paper;
import cn.edu.buaa.scholarshipserver.es.Patent;
import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.scholarship.*;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

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

    @Autowired
    private FieldService fieldService;

    @Autowired
    private StarService starService;

    @Autowired
    private ScholarDao scholarDao;

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
    public ResponseEntity<Response> getPaperByPaperId(@PathVariable("paperId") String paperId) throws IOException {
        Map<String, Object> responseMap = new TreeMap<>();
        CorrectPaper correctPaper = paperService.getPaperByPaperId(paperId);
        responseMap.put("paper", correctPaper);
        responseMap.put("paperMap",paperService.paperMap(paperId));
        return ResponseEntity.ok(new Response(responseMap));
    }

    @GetMapping("/getPatentByPatentId/{patentId}")
    @ApiOperation(notes = "查看专利内容", value = "查看专利内容")
    public ResponseEntity<Response> getPatentByPatentId(@PathVariable("patentId") String patentId) {
        Map<String, Object> responseMap = new TreeMap<>();
        Patent patent = patentService.getPatentByPatentId(patentId);
        if(patent==null){
            return ResponseEntity.ok(new Response(404,"专利不存在",404));
        }
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
                                                        @RequestParam("size") String size) throws IOException {
        if (doctype < 0 || doctype > 2) {
            return ResponseEntity.ok(new Response(400, "文献类型不正确", 400));
        }
        return paperService.advancedSearchPaper(titleKW, abstractKW, doctype, organizationKW, authorKW, startDate, endDate, page, size);
    }

    @GetMapping("/advancedSearchPaperSortByDate")
    @ApiOperation(notes = "高级检索文献（日期排序）", value = "高级检索文献（日期排序")
    public ResponseEntity<Response> advancedSearchPaperSortByDate(@RequestParam("titleKW") String titleKW,
                                                                  @RequestParam("abstractKW") String abstractKW,
                                                                  @RequestParam("doctype") int doctype,
                                                                  @RequestParam("organizationKW") String organizationKW,
                                                                  @RequestParam("authorKW") String authorKW,
                                                                  @RequestParam("startDate") String startDate,
                                                                  @RequestParam("endDate") String endDate,
                                                                  @RequestParam("page") String page,
                                                                  @RequestParam("size") String size) throws IOException {
        if (doctype < 0 || doctype > 2) {
            return ResponseEntity.ok(new Response(400, "文献类型不正确", 400));
        }
        return paperService.advancedSearchPaperSortByDate(titleKW, abstractKW, doctype, organizationKW, authorKW, startDate, endDate, page, size);
    }

    @GetMapping("/advancedSearchPaperSortByCitationCount")
    @ApiOperation(notes = "高级检索文献（被引量排序）", value = "高级检索文献（被引量排序")
    public ResponseEntity<Response> advancedSearchPaperSortByCitationCount(@RequestParam("titleKW") String titleKW,
                                                                  @RequestParam("abstractKW") String abstractKW,
                                                                  @RequestParam("doctype") int doctype,
                                                                  @RequestParam("organizationKW") String organizationKW,
                                                                  @RequestParam("authorKW") String authorKW,
                                                                  @RequestParam("startDate") String startDate,
                                                                  @RequestParam("endDate") String endDate,
                                                                  @RequestParam("page") String page,
                                                                  @RequestParam("size") String size) throws IOException {
        if (doctype < 0 || doctype > 2) {
            return ResponseEntity.ok(new Response(400, "文献类型不正确", 400));
        }
        return paperService.advancedSearchPaperSortByCitationCount(titleKW, abstractKW, doctype, organizationKW, authorKW, startDate, endDate, page, size);
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

    @PostMapping("/claimPatent/{patentId}")
    @ApiOperation(notes = "认领专利", value = "认领专利")
    public ResponseEntity<Response> claimPatent(@PathVariable("patentId")String patentId){
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        Scholar scholar = patentService.getScholarByUserId(u.getUserID());
        if(scholar==null){
            return ResponseEntity.ok(new Response(4001,"你还未认证成为学者！",400));
        }
        Patent patent = patentService.getPatentByPatentId(patentId);
        if(patentService.getPatentNowClaimNumber(patent)>=patentService.getPatentMaxClaimNumber(patent)){
            return ResponseEntity.ok(new Response(4005,"认领人数已满！",400));
        }

        long patentid = patent.getId();
        int scholarid = scholar.getScholarid();
        if(patentService.haveClaimPatent(scholarid,patentid)){
            return ResponseEntity.ok(new Response(4002,"你已经认领了该专利！",400));
        }
        patentService.addPatentPossess(scholarid,patentid);
        return ResponseEntity.ok(new Response("认领成功！"));
    }

    @PostMapping("/backClaimPatent/{patentId}")
    @ApiOperation(notes = "退领专利", value = "退领专利")
    public ResponseEntity<Response> backClaimPatent(@PathVariable("patentId")String patentId) {
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        Scholar scholar = patentService.getScholarByUserId(u.getUserID());
        if(scholar==null){
            return ResponseEntity.ok(new Response(4001,"你还未认证成为学者！",400));
        }
        Patent patent = patentService.getPatentByPatentId(patentId);

        long patentid = patent.getId();
        int scholarid = scholar.getScholarid();
        if(!patentService.haveClaimPatent(scholarid,patentid)){
            return ResponseEntity.ok(new Response(4003,"你还未认领该专利！",400));
        }

        patentService.deletePatentPossess(scholarid,patentid);
        return ResponseEntity.ok(new Response("退领成功！"));
    }

    @GetMapping("/hotFields")
    @ApiOperation(notes = "查看热门领域", value = "查看热门领域")
    public ResponseEntity<Response> getHotFields() {
        return fieldService.getHotFields();
    }

    @PostMapping("/claimProject/{projectId}")
    @ApiOperation(notes = "认领项目", value = "认领项目")
    public ResponseEntity<Response> claimProject(@PathVariable("projectId")String projectId){
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        Scholar scholar = patentService.getScholarByUserId(u.getUserID());
        if(scholar==null){
            return ResponseEntity.ok(new Response(4001,"你还未认证成为学者！",400));
        }
        Project project = projectService.getTheProjectById(projectId);

        if(patentService.getProjectNowClaimNumber(project)>=patentService.getProjectMaxClaimNumber(project)){
            return ResponseEntity.ok(new Response(4005,"认领人数已满！",400));
        }


        long projectid = project.getProjectId();
        int scholarid = scholar.getScholarid();
        if(projectService.haveClaimProject(scholarid,projectid)){
            return ResponseEntity.ok(new Response(4002,"你已经认领该项目！",400));
        }
        projectService.addProjectPossess(scholarid,projectid);
        return ResponseEntity.ok(new Response("认领成功！"));
    }

    @PostMapping("/backClaimProject/{projectId}")
    @ApiOperation(notes = "退领项目", value = "退领项目")
    public ResponseEntity<Response> backClaimProject(@PathVariable("projectId")String projectId){
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        Scholar scholar = patentService.getScholarByUserId(u.getUserID());
        if(scholar==null){
            return ResponseEntity.ok(new Response(4001,"你还未认证成为学者！",400));
        }
        Project project = projectService.getTheProjectById(projectId);

        long projectid = project.getProjectId();
        int scholarid = scholar.getScholarid();
        if(!projectService.haveClaimProject(scholarid,projectid)){
            return ResponseEntity.ok(new Response(4003,"你还未认领该项目！",400));
        }
        projectService.deleteProjectPossess(scholarid,projectid);
        return ResponseEntity.ok(new Response("认领成功！"));
    }

    @PostMapping("/manageClaimPatent/{scholarId}/{patentId}")
    @ApiOperation(notes = "管理员指定认领专利", value = "管理员指定认领专利")
    public ResponseEntity<Response> manageClaimPatent(@PathVariable("scholarId")String scholarId,
                                                      @PathVariable("patentId")String patentId){
        long patentid = Integer.parseInt(patentId);
        int scholarid = Integer.parseInt(scholarId);
        if(patentService.haveClaimPatent(scholarid,patentid)){
            return ResponseEntity.ok(new Response(4002,"学者"+scholarId+"已经认领了该专利！",400));
        }
        patentService.addPatentPossess(scholarid,patentid);
        return ResponseEntity.ok(new Response("认领成功！"));
    }


    @PostMapping("/manageBackClaimPatent/{scholarId}/{patentId}")
    @ApiOperation(notes = "管理员指定退领专利", value = "管理员指定退领专利")
    public ResponseEntity<Response> manageBackClaimPatent(@PathVariable("scholarId")String scholarId,
                                                    @PathVariable("patentId")String patentId) {
        long patentid = Integer.parseInt(patentId);
        int scholarid = Integer.parseInt(scholarId);
        if(!patentService.haveClaimPatent(scholarid,patentid)){
            return ResponseEntity.ok(new Response(4003,"学者"+scholarId+"还未认领该专利！",400));
        }

        patentService.deletePatentPossess(scholarid,patentid);
        return ResponseEntity.ok(new Response("退领成功！"));
    }

    @PostMapping("/manageClaimProject/{scholarId}/{projectId}")
    @ApiOperation(notes = "管理员指定认领项目", value = "管理员指定认领项目")
    public ResponseEntity<Response> manageClaimProject(@PathVariable("scholarId")String scholarId,
                                                       @PathVariable("projectId")String projectId){
        long projectid = Integer.parseInt(projectId);
        int scholarid = Integer.parseInt(scholarId);
        if(projectService.haveClaimProject(scholarid,projectid)){
            return ResponseEntity.ok(new Response(4002,"学者"+scholarId+"已经认领该项目！",400));
        }
        projectService.addProjectPossess(scholarid,projectid);
        return ResponseEntity.ok(new Response("认领成功！"));
    }

    @PostMapping("/manageBackClaimProject/{scholarId}/{projectId}")
    @ApiOperation(notes = "管理员指定退领项目", value = "管理员指定退领项目")
    public ResponseEntity<Response> manageBackClaimProject(@PathVariable("scholarId")String scholarId,
                                                     @PathVariable("projectId")String projectId){
        long projectid = Integer.parseInt(projectId);
        int scholarid = Integer.parseInt(scholarId);
        if(!projectService.haveClaimProject(scholarid,projectid)){
            return ResponseEntity.ok(new Response(4003,"学者"+scholarId+"还未认领该项目！",400));
        }
        projectService.deleteProjectPossess(scholarid,projectid);
        return ResponseEntity.ok(new Response("认领成功！"));
    }


    @GetMapping("/getHaveClaim/{type}/{scholarShipId}")
    @ApiOperation(notes = "查看是否已经认领", value = "查看是否已经认领")
    public ResponseEntity<Response> getHaveClaim(@PathVariable("type")String type,
                                                 @PathVariable("scholarShipId")String scholarShipId) {
        User u = (User) SecurityUtils.getSubject().getPrincipal();
        if(u==null){
            return ResponseEntity.ok(new Response(4000,"用户未登录！",400));
        }
        Scholar scholar = patentService.getScholarByUserId(u.getUserID());
        if(scholar==null){
            return ResponseEntity.ok(new Response(4001,"你还未认证成为学者！",400));
        }
        int scholarid = scholar.getScholarid();
        boolean haveClaim = false;
        if(type.equals("1")) {
            int projectid = Integer.parseInt(scholarShipId);
            haveClaim = projectService.haveClaimProject(scholarid,projectid);
        }else if(type.equals("2")){
            int patentid = Integer.parseInt(scholarShipId);
            haveClaim = patentService.haveClaimPatent(scholarid,patentid);
        }else{
            return ResponseEntity.ok(new Response(400,"学术成果类型不正确！",400));
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("haveClaim",haveClaim);
        return ResponseEntity.ok(new Response(responseMap));
    }

    @PutMapping("/starStatus")
    @ApiOperation(notes = "修改收藏状态", value = "修改收藏状态")
    public ResponseEntity<Response> changeStarStatus(@RequestBody ChangeStarStatus request) {
        User u = (User)SecurityUtils.getSubject().getPrincipal();
        if(u == null)
            return ResponseEntity.ok(new Response(403,"请先登录！",0));
        return starService.changeStarStatus(u.getUserID(), request.getPaperId(), request.getType());
    }

    @GetMapping("/starStatus")
    @ApiOperation(notes = "获取收藏状态", value = "获取收藏状态")
    public ResponseEntity<Response> getStarStatusRes (@RequestParam("paperId") Long paperId,
                                                      @RequestParam("type") Integer type) {
        User u = (User)SecurityUtils.getSubject().getPrincipal();
        if(u == null)
            return ResponseEntity.ok(new Response(403,"请先登录！",0));
        int code = starService.getStarStatus(u.getUserID(), paperId, type);
        switch (code) {
            case 0: return ResponseEntity.ok(new Response("已收藏该文献", 0));
            case 1: return ResponseEntity.ok(new Response(404, "未收藏该文献！", 0));
            case 2: return ResponseEntity.ok(new Response("已收藏该专利", 0));
            case 3: return ResponseEntity.ok(new Response(404, "未收藏该专利！", 0));
            case 4: return ResponseEntity.ok(new Response("已收藏该项目", 0));
            case 5: return ResponseEntity.ok(new Response(404, "未收藏该项目！", 0));
            default: return ResponseEntity.ok(new Response(400, "type的值只能为0,1,2！", 0));
        }
    }

    @GetMapping("/staredPaper")
    @ApiOperation(notes = "获取已收藏文献列表", value = "获取已收藏文献列表")
    public ResponseEntity<Response> getStaredPaper () {
        User u = (User)SecurityUtils.getSubject().getPrincipal();
        if(u == null)
            return ResponseEntity.ok(new Response(403,"请先登录！",0));
        return starService.getStaredPaper(u.getUserID());
    }

    @GetMapping("/staredPatent")
    @ApiOperation(notes = "获取已收藏专利列表", value = "获取已收藏专利列表")
    public ResponseEntity<Response> getStaredPatent () {
        User u = (User)SecurityUtils.getSubject().getPrincipal();
        if(u == null)
            return ResponseEntity.ok(new Response(403,"请先登录！",0));
        return starService.getStaredPatent(u.getUserID());
    }

    @GetMapping("/staredProject")
    @ApiOperation(notes = "获取已收藏项目列表", value = "获取已收藏项目列表")
    public ResponseEntity<Response> getStaredProject () {
        User u = (User)SecurityUtils.getSubject().getPrincipal();
        if(u == null)
            return ResponseEntity.ok(new Response(403,"请先登录！",0));
        return starService.getStaredProject(u.getUserID());
    }

    @GetMapping("/getWhoClaimIt/{type}/{scholarShipId}")
    @ApiOperation(notes = "通过scholarShipId查询认领了它的人的列表",
            value = "通过scholarShipId查询认领了它的人的列表")
    public ResponseEntity<Response> getWhoClaimIt(@PathVariable("type") String type,
                                                  @PathVariable("scholarShipId") String scholarShipId) {
        return patentService.getWhoClaimIt(type,scholarShipId);
    }

    @GetMapping("/getClaimNumber/{type}/{scholarShipId}")
    @ApiOperation(notes = "检测认领者是否超过数量",value = "检测认领者是否超过数量")
    public  ResponseEntity<Response> getClaimNumber(@PathVariable("type") String type,
                                                    @PathVariable("scholarShipId") String scholarShipId) {
        HashMap<String,Object> responseMap = new HashMap<>();
        int nowClaimNumber;
        int maxClaimNumber;
        if(type.equals("1")) {
            Project project = projectService.getTheProjectById(scholarShipId);
            nowClaimNumber = patentService.getProjectNowClaimNumber(project);
            maxClaimNumber = patentService.getProjectMaxClaimNumber(project);

        }else if(type.equals("2")){
            Patent patent = patentService.getPatentByPatentId(scholarShipId);
            nowClaimNumber = patentService.getPatentNowClaimNumber(patent);
            maxClaimNumber = patentService.getPatentMaxClaimNumber(patent);
        }else{
            return ResponseEntity.ok(new Response(400,"学术成果类型不正确！",400));
        }
        responseMap.put("nowClaimNumber",nowClaimNumber);
        responseMap.put("maxClaimNumber",maxClaimNumber);
        responseMap.put("canClaim",nowClaimNumber<maxClaimNumber);
        return ResponseEntity.ok(new Response(responseMap));
    }

}
