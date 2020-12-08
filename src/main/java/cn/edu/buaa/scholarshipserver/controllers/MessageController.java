package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.Message;
import cn.edu.buaa.scholarshipserver.services.message.MessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/user")
    public int sendMessage(String messageTitle,
                           String messageContent,
                           Integer sender_userid,
                           Integer receiver_userid) {
        return messageService.sendUserMessage(messageTitle, messageContent, sender_userid, receiver_userid);
    }

    @GetMapping("/user")
    public List<Message> getUserMessages(Integer userId) {
        return messageService.getUserMessages(userId);
    }

    @PutMapping("/mailbox")
    public int messageRead(Integer messageId) {
        return messageService.messageRead(messageId);
    }

    @DeleteMapping("/mailbox")
    public int deleteMessage(Integer messageId) {
        return messageService.deleteMessage(messageId);
    }

    @PostMapping("/appeal")
    public int makeAppeal(Integer userId,
                          Map.Entry< String, Long > scholarshipId,
                          MultipartFile complaintMaterial,
                          String messageTitle,
                          String messageContent) {
        return messageService.makeAppeal(userId, scholarshipId, complaintMaterial, messageTitle, messageContent);
    }

}

/*
    ***MODEL***

    Message
    (
        private Integer msgid;
        private Long paperid;
        private Long patentid;
        private Long projectid;
        private Integer userid;
        private Integer useUserid;
        private String msgtitle;
        private String msgcontent;
        private Integer msgstatus;
        private Date sendtime;
        private Integer msgtype;
    );

    ***MODEL END***

    ***Database Table***

    Message
    (
        MsgId                int not null,
        PaperId              bigint,
        PatentId             bigint,
        ProjectId            bigint,
        SenderUserID         int,
        ReceiverUserID       int,
        MsgTitle             varchar(30) not null,
        MsgContent           varchar(500) not null,
        MsgStatus            int not null,
        SendTime             datetime not null,
        MsgType              int not null,
        primary key (MsgId)
    );

    ***Database Table END***
*/
