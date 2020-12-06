package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.services.ScholarService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schlorship")
@Api(tags = {"学者门户相关接口"})

public class ScholarController {
    @Autowired
    private ScholarService scholarService;
    /*
    @GetMapping("/{ScholarId}")
    public ResponseEntity<Response> GetScholar(@PathVariable("ScholarId") String ScholarId) {

    }

    @PutMapping("/{ScholarId}")
    public ResponseEntity<Response> PutScholar(@PathVariable("ScholarId") String ScholarId) {

    }

    @GetMapping("/sameName/{UserName}")
    public ResponseEntity<Response> GetSameNameUser(@PathVariable("UserName") String UserName) {

    }*/

    @PostMapping("/Scholar_DataScholar")
    @ApiOperation(value = "为ScholarId 的门户中添加一个数据库门户authorId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="scholarId",value="学者Id",required=true,paramType="body"),
            @ApiImplicitParam(name="authorId",value="数据库门户Id",required=true,paramType="body")
    })
    public ResponseEntity<Response> PostScholar_DataScholar(@RequestParam String scholarId,@RequestParam String authorId) {
        Map<String,Integer> params = new HashMap<String,Integer>();
        params.put("scholarId",Integer.valueOf(scholarId));
        params.put("authorId",Integer.valueOf(authorId));
        return scholarService.PostScholar_DataScholar(params);
    }
/*
    @GetMapping("/admin/{ScholarName}/{ScholarId}")
    public ResponseEntity<Response> GetAdminScholar(@PathVariable("ScholarName") String ScholarName,
                                                    @PathVariable("ScholarId") String ScholarId) {

    }

    @PostMapping("/admin/{ScholarId}/{DataScholarId}")
    public ResponseEntity<Response> GetAdminScholar(@PathVariable("ScholarId") String ScholarId,
                                                    @PathVariable("DataScholarId") String DataScholarId) {

    }

    @DeleteMapping("/admin/{ScholarId}/{DataScholarId}")
    public ResponseEntity<Response> DeleteAdminScholar(@PathVariable("ScholarId") String ScholarId, @PathVariable("DataScholarId") String DataScholarId){

    }
    */
    @GetMapping("/subscribe/{userId}")
    @ApiOperation(value = "查找UserId的关注列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户Id",required=true,paramType="path"),
    })
    public ResponseEntity<Response> GetSubscribe(@PathVariable("userId") String userId) {
        Integer a = Integer.valueOf(userId);
        return scholarService.GetSubscribe(a);
    }

    @PostMapping("/subscribe/{UserId}/{ScholarId}")
    @ApiOperation(value = "向UserId的关注列表中添加ScholarId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="UserId",value="用户Id",required=true,paramType="path"),
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path")
    })
    public ResponseEntity<Response> PostSubscribe(@PathVariable("UserId") String userId, @PathVariable("ScholarId") String scholarId) {
        Integer UserId = Integer.valueOf(userId);
        Integer ScholarId = Integer.valueOf(scholarId);
        return scholarService.PostSubscribe(UserId,ScholarId);
    }

    @DeleteMapping("/subscribe/{UserId}/{ScholarId}")
    @ApiOperation(value = "在UserId的关注列表中删除ScholarId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="UserId",value="用户Id",required=true,paramType="path"),
            @ApiImplicitParam(name="ScholarId",value="学者Id",required=true,paramType="path")
    })
    public ResponseEntity<Response> DeleteSubscribe(@PathVariable("UserId") String userId, @PathVariable("ScholarId") String scholarId) {
        Integer UserId = Integer.valueOf(userId);
        Integer ScholarId = Integer.valueOf(scholarId);
        return scholarService.DeleteSubscribe(UserId,ScholarId);
    }
/*
    @PostMapping("/Scholar/Search")
    public ResponseEntity<Response> PostSearch() {

    }

    @PostMapping("/Message")
    public ResponseEntity<Response> PostMessage() {

    }
 */
}


