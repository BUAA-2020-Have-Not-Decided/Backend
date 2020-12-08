package cn.edu.buaa.scholarshipserver.services.message;

import cn.edu.buaa.scholarshipserver.dao.MessageMapper;
import cn.edu.buaa.scholarshipserver.models.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    private final MessageMapper messageMapper;

    MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public int sendUserMessage(String messageTitle, String messageContent, Integer sender_userid, Integer receiver_userid) {
        Message newMessage = new Message(null, null, null, null, null, sender_userid, receiver_userid, messageTitle, messageContent, 0, new Date(), 1);
        return messageMapper.insertSelective(newMessage);
    }

    public List<Message> getUserMessages(Integer userId) {
        List<Message> userMessages = messageMapper.findByReceiverUserId(userId);
        userMessages.removeIf(message -> message.getMsgstatus() == 2);
        return userMessages;
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

    public int makeAppeal(Integer userId, Map.Entry<String, Long> scholarshipId, MultipartFile complaintMaterial, String messageTitle, String messageContent) {
        Message newMessage = new Message(null, null, null, null, null, userId, 0, messageTitle, messageContent, 0, new Date(), 2);
        switch (scholarshipId.getKey()) {
            case "PaperId":
                newMessage.setPaperid(scholarshipId.getValue());
                break;
            case "PatentId":
                newMessage.setPatentid(scholarshipId.getValue());
                break;
            case "ProjectId":
                newMessage.setProjectid(scholarshipId.getValue());
                break;
            default:
                System.out.println("wrong parameter name");
        }
        return messageMapper.insertSelective(newMessage);
    }

}
