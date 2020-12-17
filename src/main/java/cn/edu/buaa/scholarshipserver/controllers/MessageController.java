package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.message.MessageService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                Integer receiver_userid) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return messageService.sendUserMessage(messageTitle, messageContent, user.getUserID(), receiver_userid);
    }

    @GetMapping("/user/inbox")
    public ResponseEntity<Response> getMessages() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return messageService.getMessages(user.getUserID());
    }

    @GetMapping("/user/one")
    public ResponseEntity<Response> getMessage(Integer messageId) {
        return messageService.getMessage(messageId);
    }

    @PutMapping("/user/one")
    public ResponseEntity<Response> markMessageAsRead(Integer messageId) {
        return messageService.markMessageAsRead(messageId);
    }

    @DeleteMapping("/user/one")
    public ResponseEntity<Response> deleteMessage(Integer messageId) {
        return messageService.deleteMessage(messageId);
    }

    @PostMapping("/scholar/appeal/one")
    public ResponseEntity<Response> makeAppeal(Long towardsId,
                                               String towardsType,
                                               String complaintMaterial,
                                               String messageTitle,
                                               String messageContent) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return messageService.makeAppeal(user.getUserID(), towardsId, towardsType, complaintMaterial, messageTitle, messageContent);
    }

    @GetMapping("/admin/appeal/all")
    public ResponseEntity<Response> getAppeals() {
        return messageService.getAppeals();
    }

    @PutMapping("/admin/appeal/one")
    public ResponseEntity<Response> updateAppealMessageStatus(Integer messageId, Integer messageStatus) {
        return messageService.updateAppealMessageStatus(messageId, messageStatus);
    }

}
