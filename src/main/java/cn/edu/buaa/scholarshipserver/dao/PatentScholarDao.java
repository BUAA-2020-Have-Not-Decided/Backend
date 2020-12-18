package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Paper;
import cn.edu.buaa.scholarshipserver.es.Patent;
import cn.edu.buaa.scholarshipserver.es.Patent_Scholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PatentScholarDao extends ElasticsearchRepository<Patent_Scholar,Long> {
        public List<Patent_Scholar> findByScholarId(Integer scholarId);
        public Patent_Scholar findByScholarIdAndPatentId(int scholarId,Long patentId);
        public List<Patent_Scholar> findByPatentId(Long patentId);
}
