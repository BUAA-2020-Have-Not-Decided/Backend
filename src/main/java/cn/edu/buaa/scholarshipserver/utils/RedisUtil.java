package cn.edu.buaa.scholarshipserver.utils;

import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.models.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisUtil {
    @Resource
    private RedisTemplate<String, User> redis_template;

    @Resource
    private RedisTemplate<String, Scholar> redis_scholar_template;

    /*根据一个用户的邮箱验证码获得这个用户*/
    public User getUserByString(String src){
        return redis_template.opsForValue().get(src);
    }

    /*设置一个用户的邮箱验证码和这个用户的关系，时效是十分钟*/
    public void setUserAndCode(User u, String code){
        redis_template.opsForValue().set(code, u, 10, TimeUnit.MINUTES);
    }

    /*设置一个用户和自己的jwt的关系*/
    public void setUserAndJWT(User u, String jwt){
        redis_template.opsForValue().set(jwt, u);
    }

    /*根据key移除掉value*/
    public void removeItemByKey(String code){
        redis_template.delete(code);
    }

    /*给一个Scholar设置验证码，时效是十分钟*/
    public void setScholarAndCode(Scholar s, String code){
        this.redis_scholar_template.opsForValue().set(code, s, 10, TimeUnit.MINUTES);
    }

    /*根据code获取scholar*/
    public Scholar getScholarByCode(String code){
        return this.redis_scholar_template.opsForValue().get(code);
    }

    /*根据jwt获得用户*/
    public User getUserByJWT(String jwt){
        return redis_template.opsForValue().get(jwt);
    }

    public void refreshJWTForUser(String original_jwt, String new_jwt, User u){
        if(this.redis_template.hasKey(original_jwt)){//没有，那就设置一个新的
            this.redis_template.delete(original_jwt);
        }
        this.redis_template.opsForValue().set(new_jwt, u, 30, TimeUnit.MINUTES);
    }
}
