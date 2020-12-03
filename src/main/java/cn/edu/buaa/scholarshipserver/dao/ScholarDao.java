package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Scholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ScholarDao extends ElasticsearchRepository<Scholar,Long> {
    public Scholar findByScholarId(int ScholarId);
    public List<Scholar> findByNormalizedName(String normalizedname);
}
