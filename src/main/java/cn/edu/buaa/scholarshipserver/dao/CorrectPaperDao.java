package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CorrectPaperDao extends ElasticsearchRepository<CorrectPaper,Long> {
    public CorrectPaper findByPaperId(Long paperId);
}
