package cn.edu.buaa.scholarshipserver.utils;

import cn.edu.buaa.scholarshipserver.models.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
    @Resource
    private RedisTemplate<String, User> redis_template;

    /*根据一个用户的邮箱验证码获得这个用户*/
    public User getUserByString(String src){
        return redis_template.opsForValue().get(src);
    }

    /*设置一个用户的邮箱验证码和这个用户的关系，时效是五分钟*/
    public void setUserAndCode(User u, String code){
        redis_template.opsForValue().set(code, u, 5, TimeUnit.MINUTES);
    }

    /*根据key移除掉value*/
    public void removeUserByKey(String code){
        redis_template.delete(code);
    }
}
