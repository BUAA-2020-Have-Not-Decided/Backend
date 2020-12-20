package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.es.WorkExperience;
import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.services.ScholarService;
import cn.edu.buaa.scholarshipserver.services.UploadService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scholar")
@Api(tags = {"学者门户相关接口"})

public class ScholarController {
    @Autowired
    private ScholarService scholarService;
    @Autowired
    private UploadService uploadService;
    @GetMapping("/info/{ScholarId}")
    @ApiOperation(value = "获得学者门户相关信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path",dataType = "String")
    })
    public ResponseEntity<Response> GetScholar(@PathVariable("ScholarId") String scholarId) {
        System.out.println(scholarId);
        return scholarService.GetScholar(Integer.valueOf(scholarId));
    }
    @GetMapping("/dataScholar/{AuthorId}")
    public ResponseEntity<Response> GetDataScholar(@PathVariable("AuthorId") String authorId){
        return scholarService.GetDataScholar(Long.parseLong(authorId));
    }
    @PostMapping("/image/{ScholarId}")
    @ApiOperation(value = "上传学者头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path",dataType = "String"),
            @ApiImplicitParam(name="base64Data",value = "图片的base64编码",required = true,paramType = "body",dataType = "String")
    })
    public ResponseEntity<Response> UploadImage(@PathVariable String ScholarId,@RequestBody String base64Data){
        return uploadService.UploadImage(Integer.parseInt(ScholarId),base64Data);
    }
    @PutMapping("/infoUpdate/{ScholarId}")
    @ApiOperation(value = "更新学者门户相关信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path",dataType = "String"),
            @ApiImplicitParam(name="name",value = "更改后的学者名称",required = false,paramType = "body",dataType = "String"),
            @ApiImplicitParam(name="email",value = "更改后学者邮箱",required = false,paramType = "body",dataType = "String"),
            @ApiImplicitParam(name = "phone",value = "更改后的学者姓名",required = false,paramType = "body",dataType = "String"),
            @ApiImplicitParam(name = "title",value = "更改后的学者头衔",required = false,paramType = "body",dataType = "String"),
            @ApiImplicitParam(name = "introduction",value = "更改后的学者个人简介",required = false,paramType = "body",dataType = "String"),
            @ApiImplicitParam(name ="organization",value = "更改后的学者所属组织",required = false,paramType = "body",dataType = "String")
    })
    public ResponseEntity<Response> PutScholar(@PathVariable("ScholarId") String scholarId,@RequestBody Map<String,Object> params)  {
        return scholarService.PutScholar(Integer.parseInt(scholarId),params);
    }
    @PostMapping("/workExperience")
    @ApiOperation(value="更新学者工作简历")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "workExperienceList",value = "学者所填工作经历列表",required = true,paramType = "body",dataType = "List<WorkExperience>")
    })
    public ResponseEntity<Response> PutWorkExperience(@RequestBody WorkExperience workExperienceList){
        return scholarService.PutWorkExperience(workExperienceList);
    }
    @DeleteMapping("/workExperience")
    public ResponseEntity<Response> DeleteWorkExperience(@RequestBody WorkExperience workExperience){
        return scholarService.DeleteWorkExperience(workExperience);
    }
    @GetMapping("/sameName/{UserName}/{ScholarId}")
    @ApiOperation(value = "推送同名学者")
    @ApiImplicitParams({
            @ApiImplicitParam(name="UserName",value="学者名字",required=true,paramType="path",dataType = "String")
    })
    public ResponseEntity<Response> GetSameNameUser(@PathVariable("UserName") String userName,@PathVariable("ScholarId") String scholarId) {
       return scholarService.GetSameNameUser(userName,scholarId);
    }

    @GetMapping("/Scholar_DataScholar")
    @ApiOperation(value = "为ScholarId的门户中添加一个数据库门户authorId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="scholarId",value="学者Id",required=true,paramType="body"),
    })
    public ResponseEntity<Response> GetScholar_DataScholar(@RequestParam("scholarId") String scholarId) {
        return scholarService.GetScholar_DataScholar(Integer.valueOf(scholarId));
    }

    @PostMapping("/Scholar_DataScholar")
    @ApiOperation(value = "为ScholarId的门户中添加一个数据库门户authorId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="scholarId",value="学者Id",required=true,paramType="body"),
            @ApiImplicitParam(name="authorId",value="数据库门户Id",required=true,paramType="body")
    })
    public ResponseEntity<Response> PostScholar_DataScholar(@RequestParam("scholarId") String scholarId,@RequestParam("authorId") String authorId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("scholarId",Integer.valueOf(scholarId));
        params.put("authorId",Long.valueOf(authorId));
        return scholarService.PostScholar_DataScholar(params);
    }

    @DeleteMapping("/Scholar_DataScholar")
    @ApiOperation(value = "将ScholarId的门户中的数据库门户authorId删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name="scholarId",value="学者Id",required=true,paramType="body"),
            @ApiImplicitParam(name="authorId",value="数据库门户Id",required=true,paramType="body")
    })
    public ResponseEntity<Response> DeleteScholar_DataScholar(@RequestParam("scholarId") String scholarId,@RequestParam("authorId") String authorId) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("scholarId",Integer.valueOf(scholarId));
        params.put("authorId",Long.valueOf(authorId));
        return scholarService.DeleteScholar_DataScholar(params);
    }

    @GetMapping("/admin/Search")
    @ApiOperation(value = "查找学者,按照顺序,没有为空字符串")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ScholarName",value="学者名字",required=true,paramType="path"),
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path")
    })
    public ResponseEntity<Response> GetAdminScholar(@RequestParam("ScholarName") String ScholarName,
                                                    @RequestParam("ScholarId") String ScholarId) {
        return scholarService.GetAdminScholar(ScholarName,ScholarId);
    }

    @GetMapping("/subscribe/")
    @ApiOperation(value = "查找UserId的关注列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",required=true,paramType="path"),
    })
    public ResponseEntity<Response> GetSubscribe() {
        return scholarService.GetSubscribe();
    }

    @PostMapping("/subscribe/{ScholarId}")
    @ApiOperation(value = "向UserId的关注列表中添加ScholarId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="UserId",value="用户Id",required=true,paramType="path"),
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path")
    })
    public ResponseEntity<Response> PostSubscribe(@PathVariable("ScholarId") String scholarId) {
        Integer ScholarId = Integer.valueOf(scholarId);
        return scholarService.PostSubscribe(ScholarId);
    }

    @DeleteMapping("/subscribe/{ScholarId}")
    @ApiOperation(value = "在UserId的关注列表中删除ScholarId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="UserId",value="用户Id",required=true,paramType="path"),
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path")
    })
    public ResponseEntity<Response> DeleteSubscribe( @PathVariable("ScholarId") String scholarId) {
        Integer ScholarId = Integer.valueOf(scholarId);
        return scholarService.DeleteSubscribe(ScholarId);
    }

    @GetMapping("/Search")
    @ApiOperation(value = "按照名字和机构查询学者")
    @ApiImplicitParams({
            @ApiImplicitParam(name="UserId",value="用户Id",required=true,paramType="path"),
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path")
    })
    public ResponseEntity<Response> Search(@RequestParam("ScholarName") String ScholarName
                                                ,@RequestParam("Institution") String Institution
                                                ,@RequestParam("orderType") Integer orderType
                                                ,@RequestParam("pageNumber") Integer pageNumber) {
            return scholarService.Search(ScholarName,Institution,orderType,pageNumber);
    }
    @GetMapping("/SearchDataScholar")
    @ApiOperation(value = "按照名字和机构查询学者")
    @ApiImplicitParams({
            @ApiImplicitParam(name="UserId",value="用户Id",required=true,paramType="path"),
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path")
    })
    public ResponseEntity<Response> SearchDataScholar(@RequestParam("DataScholarName") String ScholarName
            ,@RequestParam("orderType") Integer orderType
            ,@RequestParam("pageNumber") Integer pageNumber) {
        return scholarService.SearchDataScholar(ScholarName,orderType,pageNumber);
    }
}


