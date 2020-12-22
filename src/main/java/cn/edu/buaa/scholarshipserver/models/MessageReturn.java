package cn.edu.buaa.scholarshipserver.models;

public class MessageReturn extends Message {
    private String senderUsername;
    private String senderAvatar;

    public MessageReturn(Message message, String senderUsername, String senderAvatar) {
        super(message.getMsgid(),
                message.getScholarId(),
                message.getDataScholarId(),
                message.getPaperid(),
                message.getPatentid(),
                message.getProjectid(),
                message.getComplaintMaterialUrl(),
                message.getSenderUserid(),
                message.getReceiverUserid(),
                message.getMsgtitle(),
                message.getMsgcontent(),
                message.getMsgstatus(),
                message.getSendtime(),
                message.getMsgtype());

        this.senderUsername = senderUsername;
        this.senderAvatar = senderAvatar;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

}
