package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Scholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ScholarDAO extends ElasticsearchRepository<Scholar,Long> {
    Scholar findByscholarid(int ScholarId);
}
