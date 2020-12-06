package cn.edu.buaa.scholarshipserver.dao;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
public interface ScholarDao extends ElasticsearchRepository<Scholar,Long> {
    public Scholar findByScholarId(int scholarId);
    public List<Scholar> findByName(String name);
    public Scholar findByOrganization (String organization);
}

