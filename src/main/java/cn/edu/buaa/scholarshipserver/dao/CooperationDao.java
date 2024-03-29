package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Cooperation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CooperationDao extends ElasticsearchRepository<Cooperation,Long> {
        public List<Cooperation> findByAuthorId1(Long authorId1);
        public List<Cooperation> findByAuthorId2(Long authorId2);
        public List<Cooperation> findByAuthorId1OrAuthorId2(Long authorId1,Long authorId2);
}
