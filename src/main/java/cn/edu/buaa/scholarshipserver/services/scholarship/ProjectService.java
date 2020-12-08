package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.ProjectDao;
import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Component
public class ProjectService {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ProjectDao projectDao;

    public ResponseEntity<Response> getProjectById(String projectId) {
        Long id = Long.parseLong(projectId);
        Optional<Project> project = projectDao.findById(id);
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("project",project);
        return ResponseEntity.ok(new Response(responseMap));
    }
}
