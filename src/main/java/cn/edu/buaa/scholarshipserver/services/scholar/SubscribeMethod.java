package cn.edu.buaa.scholarshipserver.services.scholar;

import cn.edu.buaa.scholarshipserver.dao.SubscribeDao;
import cn.edu.buaa.scholarshipserver.es.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubscribeMethod {
    @Autowired
    private SubscribeDao subscribeDao;

    public List<Subscribe> getSubscribeByFansId(Integer FansId){
        return subscribeDao.findByFanId(FansId);
    }
}
