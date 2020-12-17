package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Institution;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface InstitutionDao extends ElasticsearchRepository<Institution,Long> {
        public Institution findByInstitutionId(Long institutionId);
        public Institution findByInstitutionName(String institutionName);
}
