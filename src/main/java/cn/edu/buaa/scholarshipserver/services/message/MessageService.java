package cn.edu.buaa.scholarshipserver.services.message;

import cn.edu.buaa.scholarshipserver.dao.MessageMapper;
import cn.edu.buaa.scholarshipserver.models.Message;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Response> sendUserMessage(String messageTitle,
                                                    String messageContent,
                                                    Integer sender_userid,
                                                    Integer receiver_userid) {
        Message newMessage = new Message(null, null, null, null, null, sender_userid, receiver_userid, messageTitle, messageContent, 0, new Date(), 1);
        try {
            messageMapper.insertSelective(newMessage);
            return ResponseEntity.ok(new Response("message sent", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> getMessages(Integer userId) {
        try {
            List<Message> userMessages = messageMapper.findByReceiverUserId(userId);
            userMessages.removeIf(message -> message.getMsgstatus() == 2);
            return ResponseEntity.ok(new Response(userMessages));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> getMessage(Integer messageId) {
        try {
            Message message = messageMapper.selectByPrimaryKey(messageId);
            return ResponseEntity.ok(new Response(message));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> markMessageAsRead(Integer messageId) {
        try {
            Message message = messageMapper.selectByPrimaryKey(messageId);
            message.setMsgstatus(1);
            messageMapper.updateByPrimaryKeySelective(message);
            return ResponseEntity.ok(new Response("done", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> deleteMessage(Integer messageId) {
        try {
            Message message = messageMapper.selectByPrimaryKey(messageId);
            message.setMsgstatus(2);
            messageMapper.updateByPrimaryKeySelective(message);
            return ResponseEntity.ok(new Response("done", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> makeAppeal(Integer userId,
                                               Long scholarshipId,
                                               String scholarshipType,
                                               MultipartFile complaintMaterial,
                                               String messageTitle,
                                               String messageContent) {
        try {
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
            messageMapper.insertSelective(newMessage);
            return ResponseEntity.ok(new Response("done", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
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

    public ResponseEntity<Response> getAppeals() {
        try {
            List<Message> appeals = messageMapper.findByReceiverUserId(0);
            appeals.removeIf(message -> message.getMsgstatus() == 2);
            return ResponseEntity.ok(new Response(appeals));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> updateAppealMessageStatus(Integer messageId, Integer messageStatus) {
        try {
            Message message = messageMapper.selectByPrimaryKey(messageId);
            message.setMsgstatus(messageStatus);
            messageMapper.updateByPrimaryKeySelective(message);
            return ResponseEntity.ok(new Response("done", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

}
