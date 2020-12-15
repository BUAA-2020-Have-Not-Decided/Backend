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

    public List<Subscribe> getSubscribeByFanId(Integer FanId){
        return subscribeDao.findByFanId(FanId);
    }
    public Subscribe getSubscribeByFanIdAndScholarId(Integer FanId,Long ScholarId){
        return subscribeDao.findByFanIdAndScholarId(FanId,ScholarId);
    }
    public void updateSubscribe(Subscribe subscribe){
        subscribeDao.save(subscribe);
    }
    public void deleteSubscribe(Subscribe subscribe){
        subscribeDao.delete(subscribe);
    }
}
