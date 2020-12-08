package cn.edu.buaa.scholarshipserver.services.message;

import cn.edu.buaa.scholarshipserver.dao.MessageMapper;
import cn.edu.buaa.scholarshipserver.models.Message;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public List<Message> getUserMessages(Integer userId) {
        return messageMapper.findByReceiverUserId(userId);
    }

    public int messageRead(Integer messageId) {
        Message message = messageMapper.selectByPrimaryKey(messageId);
        message.setMsgstatus(1);
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    public int deleteMessage(Integer messageId) {
        Message message = messageMapper.selectByPrimaryKey(messageId);
        message.setMsgstatus(2);
        return messageMapper.updateByPrimaryKeySelective(message);
    }

}
