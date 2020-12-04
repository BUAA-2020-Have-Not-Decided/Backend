package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.DataScholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DataScholarDao extends ElasticsearchRepository<DataScholar,Long> {
    DataScholar findByDataScholarId(int DataScholarId);
}
