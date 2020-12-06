package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Project;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProjectDao extends ElasticsearchRepository<Project,Long> {
        public cn.edu.buaa.scholarshipserver.es.Project findByProjectId(Integer projectId);
}
