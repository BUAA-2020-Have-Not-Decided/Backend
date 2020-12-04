package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Subscribe_Scholar;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SubscribeDao extends ElasticsearchRepository<Subscribe_Scholar,Long> {
    List<Subscribe_Scholar> findByFanID(int FanID);
}
