package cn.edu.buaa.scholarshipserver.dao;


import cn.edu.buaa.scholarshipserver.es.DataScholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DataScholarDao extends ElasticsearchRepository<DataScholar,Long> {
    public List<DataScholar> findByAuthorId(int authorId);

}

