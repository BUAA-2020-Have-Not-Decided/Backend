package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Fields;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FieldDao extends ElasticsearchRepository<Fields,Long> {
    public Fields findByFieldsId(int FieldsId);
}
