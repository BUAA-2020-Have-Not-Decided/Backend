package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.CollectMapper;
import cn.edu.buaa.scholarshipserver.models.Collect_Article;
import cn.edu.buaa.scholarshipserver.models.Collect_Patent;
import cn.edu.buaa.scholarshipserver.models.Collect_Project;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StarService {

    private CollectMapper collectMapper;

    StarService(CollectMapper collectMapper) {
        this.collectMapper = collectMapper;
    }

    public int getStarStatus (Integer userId, Long paperId, Integer type) {
        switch (type) {
            case 0:
                Collect_Article collect_article = collectMapper.getCollectPaper(userId, paperId);
                if (collect_article == null) {
                    return 0;
                } else {
                    return 1;
                }
            case 1:
                Collect_Patent collect_patent = collectMapper.getCollectPatent(userId, paperId);
                if (collect_patent == null) {
                    return 2;
                } else {
                    return 3;
                }
            case 2:
                Collect_Project collect_project = collectMapper.getCollectProject(userId, paperId);
                if (collect_project == null) {
                    return 4;
                } else {
                    return 5;
                }
            default:
                return 6;
        }
    }

    public ResponseEntity<Response> getStarStatusRes (Integer userId, Long paperId, Integer type) {
        int code = getStarStatus(userId, paperId, type);
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

}
