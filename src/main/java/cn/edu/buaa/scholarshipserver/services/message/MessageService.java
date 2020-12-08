package cn.edu.buaa.scholarshipserver.services.message;

import cn.edu.buaa.scholarshipserver.dao.MessageMapper;
import cn.edu.buaa.scholarshipserver.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class MessageService {

    private final MessageMapper messageMapper;
    @Value("${files.path}")
    private String filePath;

    MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public int sendUserMessage(String messageTitle,
                               String messageContent,
                               Integer sender_userid,
                               Integer receiver_userid) {
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

    public int makeAppeal(Integer userId,
                          Long scholarshipId,
                          String scholarshipType,
                          MultipartFile complaintMaterial,
                          String messageTitle,
                          String messageContent) {
        Message newMessage = new Message(null, null, null, null, null, userId, 0, messageTitle, messageContent, 0, new Date(), 2);
        switch (scholarshipType) {
            case "paper":
                newMessage.setPaperid(scholarshipId);
                break;
            case "patent":
                newMessage.setPatentid(scholarshipId);
                break;
            case "project":
                newMessage.setProjectid(scholarshipId);
                break;
            default:
                System.out.println("wrong parameter name");
        }
        String complaintMaterialFilePath = uploadFile(complaintMaterial);
        newMessage.setComplaintMaterialUrl(complaintMaterialFilePath);

        return messageMapper.insertSelective(newMessage);
    }

    private String uploadFile(MultipartFile file) {
        String newFileName = "";
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null) {
                String extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
                newFileName = UUID.randomUUID().toString() + extensionName;
            }
            else {
                System.out.println("The file doesn't have an original file name?!");
                newFileName = UUID.randomUUID().toString();
            }
            try {
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + newFileName)));
            } catch (IOException e) {
                System.out.println("Something went wrong when writing the file to server");
            }
        } catch (NullPointerException e) {
            System.out.println("Didn't get the file");
        }

        return newFileName;
    }

}
