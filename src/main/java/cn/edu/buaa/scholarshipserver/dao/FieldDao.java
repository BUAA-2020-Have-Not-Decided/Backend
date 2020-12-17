package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Fields;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface FieldDao extends ElasticsearchRepository<Fields,Long> {
    Optional<Fields> findById(Long id);
    Fields findByFieldsId(Integer fieldsId);
}
