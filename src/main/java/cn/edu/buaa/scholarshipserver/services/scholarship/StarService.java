package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.CollectMapper;
import cn.edu.buaa.scholarshipserver.dao.CorrectPaperDao;
import cn.edu.buaa.scholarshipserver.dao.PatentDao;
import cn.edu.buaa.scholarshipserver.dao.ProjectDao;
import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.Patent;
import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.models.Collect_Article;
import cn.edu.buaa.scholarshipserver.models.Collect_Patent;
import cn.edu.buaa.scholarshipserver.models.Collect_Project;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class StarService {

    private CollectMapper collectMapper;
    private CorrectPaperDao correctPaperDao;
    private PatentDao patentDao;
    private ProjectDao projectDao;
    private PaperService paperService;

    public StarService(CollectMapper collectMapper, CorrectPaperDao correctPaperDao, PatentDao patentDao, ProjectDao projectDao, PaperService paperService) {
        this.collectMapper = collectMapper;
        this.correctPaperDao = correctPaperDao;
        this.patentDao = patentDao;
        this.projectDao = projectDao;
        this.paperService = paperService;
    }

    public int getStarStatus (Integer userId, Long paperId, Integer type) {
        switch (type) {
            case 0:
                Collect_Article collect_article = collectMapper.getCollectPaper(userId, paperId);
                if (collect_article == null) {
                    return 1;
                } else {
                    return 0;
                }
            case 1:
                Collect_Patent collect_patent = collectMapper.getCollectPatent(userId, paperId);
                if (collect_patent == null) {
                    return 3;
                } else {
                    return 2;
                }
            case 2:
                Collect_Project collect_project = collectMapper.getCollectProject(userId, paperId);
                if (collect_project == null) {
                    return 5;
                } else {
                    return 4;
                }
            default:
                return 6;
        }
    }

    public ResponseEntity<Response> changeStarStatus (Integer userId, Long paperId, Integer type) {
        int code = getStarStatus(userId, paperId, type);
        int status;
        switch (code) {
            case 0:
                status = collectMapper.cancelCollectPaper(userId, paperId);
                return ResponseEntity.ok(new Response("已取消收藏该文献", status));
            case 1:
                status = collectMapper.collectPaper(new Collect_Article(paperId, userId, new Date()));
                return ResponseEntity.ok(new Response("已收藏该文献", status));
            case 2:
                status = collectMapper.cancelCollectPatent(userId, paperId);
                return ResponseEntity.ok(new Response("已取消收藏该专利", status));
            case 3:
                status = collectMapper.collectPatent(new Collect_Patent(paperId, userId, new Date()));
                return ResponseEntity.ok(new Response("已收藏该专利", status));
            case 4:
                status = collectMapper.cancelCollectProject(userId, paperId);
                return ResponseEntity.ok(new Response("已取消收藏该项目", status));
            case 5:
                status = collectMapper.collectProject(new Collect_Project(paperId, userId, new Date()));
                return ResponseEntity.ok(new Response("已收藏该项目", status));
            default: return ResponseEntity.ok(new Response(400, "type的值只能为0,1,2！", 0));
        }
    }

    public ResponseEntity<Response> getStaredPaper (Integer userId) {
        List<Collect_Article> allCollectPaper = collectMapper.getAllCollectPaper(userId);
        List<CorrectPaper> correctPapers = new ArrayList<>();
        for (Collect_Article collect_article : allCollectPaper) {
            correctPapers.add(correctPaperDao.findByPaperId(collect_article.getPaperId()));
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("paperList", correctPapers);
        responseMap.put("total", correctPapers.size());
        try {
            responseMap.put("authors", paperService.getAuthorList(correctPapers));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new Response(500, "服务器内部异常", null));
        }
        return ResponseEntity.ok(new Response(responseMap));
    }

    public ResponseEntity<Response> getStaredPatent (Integer userId) {
        List<Collect_Patent> allCollectPatent = collectMapper.getAllCollectPatent(userId);
        List<Patent> patents = new ArrayList<>();
        for (Collect_Patent collect_patent : allCollectPatent) {
            patents.add(patentDao.findById(collect_patent.getPatentId()).orElse(null));
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("patentList", patents);
        responseMap.put("total", patents.size());
        return ResponseEntity.ok(new Response(responseMap));
    }

    public ResponseEntity<Response> getStaredProject (Integer userId) {
        List<Collect_Project> allCollectProject = collectMapper.getAllCollectProject(userId);
        List<Project> projects = new ArrayList<>();
        for (Collect_Project collect_project : allCollectProject) {
            projects.add(projectDao.findById(collect_project.getProjectId()).orElse(null));
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("projectList", projects);
        responseMap.put("total", projects.size());
        return ResponseEntity.ok(new Response(responseMap));
    }

}
