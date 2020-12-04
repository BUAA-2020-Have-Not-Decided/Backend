package cn.edu.buaa.scholarshipserver.services.message;

import cn.edu.buaa.scholarshipserver.dao.MessageMapper;
import cn.edu.buaa.scholarshipserver.models.Message;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {

    private final MessageMapper messageMapper;

    MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public int sendUserMessage(String messageTitle, String messageContent, Integer sender_userid, Integer receiver_userid) {
        Message newMessage = new Message(null, null, null, null, sender_userid, receiver_userid, messageTitle, messageContent, 0, new Date(), 1);
        return messageMapper.insertSelective(newMessage);
    }

}
