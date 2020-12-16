package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.Message;
import cn.edu.buaa.scholarshipserver.services.message.MessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user/one")
    public int sendMessage(String messageTitle,
                           String messageContent,
                           Integer sender_userid,
                           Integer receiver_userid) {
        return messageService.sendUserMessage(messageTitle, messageContent, sender_userid, receiver_userid);
    }

    @GetMapping("/user/inbox")
    public List<Message> getMessages(Integer userId) {
        return messageService.getMessages(userId);
    }

    @GetMapping("/one")
    public Message getMessage(Integer messageId) {
        return messageService.getMessage(messageId);
    }

    @PutMapping("/one")
    public int markMessageAsRead(Integer messageId) {
        return messageService.markMessageAsRead(messageId);
    }

    @DeleteMapping("/one")
    public int deleteMessage(Integer messageId) {
        return messageService.deleteMessage(messageId);
    }

    @PostMapping("/appeal/one")
    public int makeAppeal(Integer userId,
                          Long scholarshipId,
                          String scholarshipType,
                          MultipartFile complaintMaterial,
                          String messageTitle,
                          String messageContent) {
        return messageService.makeAppeal(userId, scholarshipId, scholarshipType, complaintMaterial, messageTitle, messageContent);
    }

    @GetMapping("/appeal/all")
    public List<Message> getAppeals() {
        return messageService.getAppeals();
    }

    @PutMapping("/appeal/one")
    public int updateAppealMessageStatus(Integer messageId, Integer messageStatus) {
        return messageService.updateAppealMessageStatus(messageId, messageStatus);
    }

}
