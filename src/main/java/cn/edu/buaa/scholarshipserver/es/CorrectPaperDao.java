package cn.edu.buaa.scholarshipserver.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CorrectPaperDao extends ElasticsearchRepository<CorrectPaper,Long> {
    public CorrectPaper findByPaperId(Long paperId);
}
