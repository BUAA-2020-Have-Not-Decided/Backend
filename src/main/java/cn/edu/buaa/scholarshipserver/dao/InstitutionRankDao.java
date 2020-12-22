package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.InstitutionRank;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface InstitutionRankDao extends ElasticsearchRepository<InstitutionRank, Long> {
    List<InstitutionRank> findByFieldId(Long fieldId);
}
