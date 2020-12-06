package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Article_Field;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleFieldDao extends ElasticsearchRepository<Article_Field,Long> {
    public List<Article_Field> findByPaperId(Long paperId);
}
