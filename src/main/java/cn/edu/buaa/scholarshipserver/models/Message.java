package cn.edu.buaa.scholarshipserver.models;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer msgid;
    private Long data_scholar_id;
    private Long paperid;
    private Long patentid;
    private Long projectid;
    private String complaint_material_url;
    private Integer sender_userid;
    private Integer receiver_userid;
    private String msgtitle;
    private String msgcontent;
    private Integer msgstatus;
    private Date sendtime;
    private Integer msgtype;

    public Message(Integer msgid, Long data_scholar_id, Long paperid, Long patentid, Long projectid, String complaint_material_url, Integer sender_userid, Integer receiver_userid, String msgtitle, String msgcontent, Integer msgstatus, Date sendtime, Integer msgtype) {
        this.msgid = msgid;
        this.data_scholar_id = data_scholar_id;
        this.paperid = paperid;
        this.patentid = patentid;
        this.projectid = projectid;
        this.complaint_material_url = complaint_material_url;
        this.sender_userid = sender_userid;
        this.receiver_userid = receiver_userid;
        this.msgtitle = msgtitle;
        this.msgcontent = msgcontent;
        this.msgstatus = msgstatus;
        this.sendtime = sendtime;
        this.msgtype = msgtype;
    }

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public Long getDataScholarId() {
        return data_scholar_id;
    }

    public void setDataScholarId(Long data_scholar_id) {
        this.data_scholar_id = data_scholar_id;
    }

    public Long getPaperid() {
        return paperid;
    }

    public void setPaperid(Long paperid) {
        this.paperid = paperid;
    }

    public Long getPatentid() {
        return patentid;
    }

    public void setPatentid(Long patentid) {
        this.patentid = patentid;
    }

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public String getComplaintMaterialUrl() {
        return complaint_material_url;
    }

    public void setComplaintMaterialUrl(String complaint_material_url) {
        this.complaint_material_url = complaint_material_url;
    }

    public Integer getSenderUserid() {
        return sender_userid;
    }

    public void setSenderUserid(Integer sender_userid) {
        this.sender_userid = sender_userid;
    }

    public Integer getReceiverUserid() {
        return receiver_userid;
    }

    public void setReceiverUserid(Integer receiver_userid) {
        this.receiver_userid = receiver_userid;
    }

    public String getMsgtitle() {
        return msgtitle;
    }

    public void setMsgtitle(String msgtitle) {
        this.msgtitle = msgtitle == null ? null : msgtitle.trim();
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    public Integer getMsgstatus() {
        return msgstatus;
    }

    public void setMsgstatus(Integer msgstatus) {
        this.msgstatus = msgstatus;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Integer msgtype) {
        this.msgtype = msgtype;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", msgid=" + msgid +
                ", paperid=" + paperid +
                ", patentid=" + patentid +
                ", projectid=" + projectid +
                ", complaint material url=" + complaint_material_url +
                ", sender userid=" + sender_userid +
                ", receiver userid=" + receiver_userid +
                ", msgtitle=" + msgtitle +
                ", msgcontent=" + msgcontent +
                ", msgstatus=" + msgstatus +
                ", sendtime=" + sendtime +
                ", msgtype=" + msgtype +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}
