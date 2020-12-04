package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper {

    int countRecords();

    int insertSelective(Message message);

}
