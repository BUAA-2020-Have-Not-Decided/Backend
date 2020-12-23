package cn.edu.buaa.scholarshipserver.services.message;

import cn.edu.buaa.scholarshipserver.dao.MessageMapper;
import cn.edu.buaa.scholarshipserver.dao.ScholarMapper;
import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.Message;
import cn.edu.buaa.scholarshipserver.models.MessageReturn;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;


@Service
public class MessageService {

    private final MessageMapper messageMapper;
    private final UserMapper userMapper;
    private final ScholarMapper scholarMapper;
    @Value("${files.path}")
    private String filePath;
    @Value("${files.host}")
    private String fileHost;

    MessageService(MessageMapper messageMapper, UserMapper userMapper, ScholarMapper scholarMapper) {
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
        this.scholarMapper = scholarMapper;
    }

    public ResponseEntity<Response> sendUserMessage(String messageTitle,
                                                    String messageContent,
                                                    Integer sender_userid,
                                                    Integer receiver_userid) {
        Message newMessage = new Message(null, null, null, null, null, null, null, sender_userid, receiver_userid, messageTitle, messageContent, 0, new Date(), 1);
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
            List<MessageReturn> messagesToReturn = fillMessagesToReturn(userMessages);
            return ResponseEntity.ok(new Response(messagesToReturn));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> getMessage(Integer messageId) {
        try {
            Message message = messageMapper.selectByPrimaryKey(messageId);
            MessageReturn messageToReturn = fillMessageToReturn(message);
            return ResponseEntity.ok(new Response(messageToReturn));
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
                                               Integer scholarId,
                                               Long towardsId,
                                               String towardsType,
                                               String complaintMaterialUrl,
                                               String messageTitle,
                                               String messageContent) {
        try {
            Message newMessage = new Message(null, scholarId, null, null, null, null, complaintMaterialUrl, userId, 0, messageTitle, messageContent, 0, new Date(), 2);
            switch (towardsType) {
                case "dataScholar":
                    newMessage.setDataScholarId(towardsId);
                    break;
                case "paper":
                    newMessage.setPaperid(towardsId);
                    break;
                case "patent":
                    newMessage.setPatentid(towardsId);
                    break;
                case "project":
                    newMessage.setProjectid(towardsId);
                    break;
                default:
                    return ResponseEntity.ok(new Response(400, "wrong scholarship type", ""));
            }
            messageMapper.insertSelective(newMessage);
            return ResponseEntity.ok(new Response("done", ""));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public String uploadImage(String base64Data) throws Exception {
        String dataPrix = ""; //base64格式前头
        String data = ""; //实体部分数据
        if (base64Data == null || base64Data.equals("")) {
            throw new Exception("empty picture");
        }
        else {
            String[] d = base64Data.split("base64,"); //将字符串分成数组
            if (d.length == 2) {
                dataPrix = d[0];
                data = d[1].replace(" ", "+");
            }
            else {
                throw new Exception("invalid picture");
            }
        }
        String suffix = ""; //图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        }
        else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }
        else {
            throw new Exception("invalid picture type");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newFileName = uuid + suffix;
        String imgFilePath = filePath + newFileName; //新生成的图片
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            //Base64解码
            byte[] b = decoder.decode(data);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    //调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return "http://" + fileHost + "/pictures/" + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("IOException occurred");
        }
    }

    public ResponseEntity<Response> uploadFile(MultipartFile file) {
        String newFileName = "";
        String originalFilename = file.getOriginalFilename();
        String[] allowedExtensionNames = {"pdf", "docx", "doc", "ppt", "jpg", "png"};
        if (originalFilename != null) {
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf(".")).substring(1);
            if (Arrays.asList(allowedExtensionNames).contains(extensionName)) {
                newFileName = UUID.randomUUID().toString() + "." + extensionName;
            }
            else {
                return ResponseEntity.ok(new Response(400, "not-allowed extension name"));
            }
        }
        else {
            return ResponseEntity.ok(new Response(400, "didn't get the original file name"));
        }
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + newFileName)));
        } catch (IOException e) {
            return ResponseEntity.ok(new Response(500, "Something went wrong when writing the file to server"));
        }
        return ResponseEntity.ok(new Response("http://" + fileHost + "/files/" + newFileName));
    }

    public ResponseEntity<Response> getAppeals() {
        try {
            List<Message> appeals = messageMapper.findByReceiverUserId(0);
            List<MessageReturn> appealsToReturn = fillMessagesToReturn(appeals);
            return ResponseEntity.ok(new Response(appealsToReturn));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    public ResponseEntity<Response> updateAppealMessageStatus(Integer messageId, Integer messageStatus) {
        try {
            Message message = messageMapper.selectByPrimaryKey(messageId);
            Integer currentStatus = message.getMsgstatus();
            if (!messageStatus.equals(currentStatus)) {
                message.setMsgstatus(messageStatus);
                messageMapper.updateByPrimaryKeySelective(message);
                Integer scholarId = message.getScholarId();
                Integer senderUserId = message.getSenderUserid();
                if (messageStatus == 3) {
                    Message systemMessage = new Message(null, scholarId, null, null, null,
                            null, null, 0, senderUserId, "系统消息",
                            "您的申诉已通过", 0, new Date(), 0);
                    messageMapper.insertSelective(systemMessage);
                }
                else if (messageStatus == 4) {
                    Message systemMessage = new Message(null, scholarId, null, null, null,
                            null, null, 0, senderUserId, "系统消息",
                            "抱歉，您的申诉未通过", 0, new Date(), 0);
                    messageMapper.insertSelective(systemMessage);
                }
            }
            return ResponseEntity.ok(new Response("done", ""));
        } catch (Exception e) {
            return ResponseEntity.ok(new Response(500, "something's wrong", ""));
        }
    }

    private MessageReturn fillMessageToReturn(Message message) {
        Integer senderUserId = message.getSenderUserid();
        String senderName = userMapper.getUserByID(senderUserId).getName();
        String senderAvatar = "";
        try {
             senderAvatar = scholarMapper.selectByUID(senderUserId).getAvatarurl();
        }
        catch (Exception e) {
            e.printStackTrace();
            senderAvatar = null;
        }
        return new MessageReturn(message, senderName, senderAvatar);
    }

    private List<MessageReturn> fillMessagesToReturn(List<Message> messages) {
        List<MessageReturn> messagesToReturn = new ArrayList<>();
        for (Message message : messages) {
            if (message.getMsgstatus() != 2) {
                MessageReturn messageToReturn = fillMessageToReturn(message);
                messagesToReturn.add(messageToReturn);
            }
        }
        return messagesToReturn;
    }

}
