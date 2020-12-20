package cn.edu.buaa.scholarshipserver.models;

public class MessageReturn extends Message {
    private String senderUsername;

    public MessageReturn(Message message, String senderUsername) {
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
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

}
