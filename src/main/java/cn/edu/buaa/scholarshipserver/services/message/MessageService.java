package cn.edu.buaa.scholarshipserver.services.message;

import cn.edu.buaa.scholarshipserver.dao.MessageMapper;
import cn.edu.buaa.scholarshipserver.models.Message;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class MessageService {

    private final MessageMapper messageMapper;
    @Value("${files.path}")
    private String filePath;
    @Value("${files.host}")
    private String fileHost;

    MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public ResponseEntity<Response> sendUserMessage(String messageTitle,
                                                    String messageContent,
                                                    Integer sender_userid,
                                                    Integer receiver_userid) {
        Message newMessage = new Message(null, null, null, null, null, null, sender_userid, receiver_userid, messageTitle, messageContent, 0, new Date(), 1);
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
                                               Long towardsId,
                                               String towardsType,
                                               String complaintMaterialUrl,
                                               String messageTitle,
                                               String messageContent) {
        try {
            Message newMessage = new Message(null, null, null, null, null, complaintMaterialUrl, userId, 0, messageTitle, messageContent, 0, new Date(), 2);
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

    public ResponseEntity<Response> uploadFile(MultipartFile complaintMaterial){

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
