package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Paper_DataScholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PaperDataScholarDao extends ElasticsearchRepository<Paper_DataScholar,Long> {
        List<Paper_DataScholar> findByAuthorId(Long authorId);
        List<Paper_DataScholar> findByPaperId(Long paperId);
}
