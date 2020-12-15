package cn.edu.buaa.scholarshipserver.dao;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
public interface ScholarDao extends ElasticsearchRepository<Scholar,Long> {
    public Scholar findByScholarId(Long scholarId);
    public List<Scholar> findByName(String name);
    public List<Scholar> findByOrganization (String organization);
    public List<Scholar> findByNameAndOrganization (String Name,String organization);
}

