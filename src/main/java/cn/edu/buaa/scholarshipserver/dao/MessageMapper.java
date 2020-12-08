package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {

    int countRecords();

    int insertSelective(Message message);

    List<Message> findByReceiverUserId(Integer userId);

    Message selectByPrimaryKey(Integer messageId);

    int updateByPrimaryKeySelective(Message message);

}
