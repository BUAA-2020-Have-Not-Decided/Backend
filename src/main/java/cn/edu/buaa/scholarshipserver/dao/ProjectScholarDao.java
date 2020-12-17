package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Patent_Scholar;
import cn.edu.buaa.scholarshipserver.es.Project_Scholar;
import cn.edu.buaa.scholarshipserver.models.Project;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProjectScholarDao extends ElasticsearchRepository<Project_Scholar,Long> {
        public List<Project_Scholar> findByScholarId(Integer scholarId);

        public Project_Scholar findByScholarIdAndProjectId(int scholarId,long projectId);
}
