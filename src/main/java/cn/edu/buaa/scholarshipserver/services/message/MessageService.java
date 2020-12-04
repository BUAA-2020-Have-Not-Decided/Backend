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

    public int sendMessage(String msgtitle, String msgcontent, Integer sender_userid, Integer receiver_userid) {
        int newMessageId = messageMapper.countRecords();
        Message newMessage = new Message(newMessageId, null, null, null, sender_userid, receiver_userid, msgtitle, msgcontent, 0, new Date(), 1);
        return messageMapper.insertSelective(newMessage);
    }
}
