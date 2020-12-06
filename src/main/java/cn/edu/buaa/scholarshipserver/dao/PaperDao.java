package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Paper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaperDao extends ElasticsearchRepository<Paper,Long> {
        public Paper findByPaperId(Long paperId);
}
