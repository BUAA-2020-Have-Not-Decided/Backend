package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Patent;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PatentDao extends ElasticsearchRepository<Patent,Long> {
    Patent findPatentById(Long id);
}
