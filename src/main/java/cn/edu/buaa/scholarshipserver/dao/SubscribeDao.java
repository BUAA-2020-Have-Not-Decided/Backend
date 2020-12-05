package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.es.Subscribe;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface SubscribeDao extends ElasticsearchRepository<Subscribe,Long> {
    List<Subscribe> findByFanId(int fanId);
}

