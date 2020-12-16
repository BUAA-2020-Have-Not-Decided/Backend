package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.services.message.MessageService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user/one")
    public ResponseEntity<Response> sendMessage(String messageTitle,
                                                String messageContent,
                                                Integer sender_userid,
                                                Integer receiver_userid) {
        return messageService.sendUserMessage(messageTitle, messageContent, sender_userid, receiver_userid);
    }

    @GetMapping("/user/inbox")
    public ResponseEntity<Response> getMessages(Integer userId) {
        return messageService.getMessages(userId);
    }

    @GetMapping("/one")
    public ResponseEntity<Response> getMessage(Integer messageId) {
        return messageService.getMessage(messageId);
    }

    @PutMapping("/one")
    public ResponseEntity<Response> markMessageAsRead(Integer messageId) {
        return messageService.markMessageAsRead(messageId);
    }

    @DeleteMapping("/one")
    public ResponseEntity<Response> deleteMessage(Integer messageId) {
        return messageService.deleteMessage(messageId);
    }

    @PostMapping("/appeal/one")
    public ResponseEntity<Response> makeAppeal(Integer userId,
                                               Long scholarshipId,
                                               String scholarshipType,
                                               MultipartFile complaintMaterial,
                                               String messageTitle,
                                               String messageContent) {
        return messageService.makeAppeal(userId, scholarshipId, scholarshipType, complaintMaterial, messageTitle, messageContent);
    }

    @GetMapping("/appeal/all")
    public ResponseEntity<Response> getAppeals() {
        return messageService.getAppeals();
    }

    @PutMapping("/appeal/one")
    public ResponseEntity<Response> updateAppealMessageStatus(Integer messageId, Integer messageStatus) {
        return messageService.updateAppealMessageStatus(messageId, messageStatus);
    }

}
