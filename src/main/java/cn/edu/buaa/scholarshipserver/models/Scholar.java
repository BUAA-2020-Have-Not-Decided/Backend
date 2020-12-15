package cn.edu.buaa.scholarshipserver.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Scholar implements Serializable {

    private Integer scholarid;

    private Integer institutionid;

    private String englishname;

    private String name;

    private String title;

    private String organization;

    private String email;

    private String phone;

    private Integer fans;

    private String personalpage;

    private String introduction;

    private Integer papers;

    private Integer citations;

    private Integer hindex;

    private Integer gindex;

    private Integer sociability;

    private Integer diversity;

    private Integer activity;

    private String avatarurl;

    private Date lastknowntime;

    private Integer uid;

    private static final long serialVersionUID = 1L;

    public Scholar(int uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.englishname = "";
        this.papers = 0;
        this.lastknowntime = new Date();
        this.fans = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scholarid=").append(scholarid);
        sb.append(", institutionid=").append(institutionid);
        sb.append(", englishname=").append(englishname);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", organization=").append(organization);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", fans=").append(fans);
        sb.append(", personalpage=").append(personalpage);
        sb.append(", introduction=").append(introduction);
        sb.append(", papers=").append(papers);
        sb.append(", citations=").append(citations);
        sb.append(", hindex=").append(hindex);
        sb.append(", gindex=").append(gindex);
        sb.append(", sociability=").append(sociability);
        sb.append(", diversity=").append(diversity);
        sb.append(", activity=").append(activity);
        sb.append(", avatarurl=").append(avatarurl);
        sb.append(", lastknowntime=").append(lastknowntime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
