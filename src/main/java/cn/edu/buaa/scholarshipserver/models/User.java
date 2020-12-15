package cn.edu.buaa.scholarshipserver.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /*用户的id，主键*/
    private Integer UserID;
    /*用户的登录密码*/
    private String Password;
    /*用户的用户名*/
    private String Name;
    /*用户的性别*/
    private Integer Sex;
    /*用户头像的url*/
    private String UserImagePath;
    /*用户的邮箱*/
    private String Email;
    /*用户简介*/
    private String BriefIntroduction;// 建议不展示
    /*用户的身份：0-管理员，1-普通用户，2-学者*/
    private Integer Identify;

    private static final long serialVersionUID = 1L;

    public User(String name, String password, String email){
        this.Password = password;
        this.Name = name;
        this.Email = email;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userid) {
        this.UserID = userid;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password == null ? null : password.trim();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer sex) {
        this.Sex = sex;
    }

    public String getUserImagePath() {
        return UserImagePath;
    }

    public void setUserImagePath(String userimagepath) {
        this.UserImagePath = userimagepath == null ? null : userimagepath.trim();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email == null ? null : email.trim();
    }

    public String getBriefIntroduction() {
        return BriefIntroduction;
    }

    public void setBriefIntroduction(String briefintroduction) {
        this.BriefIntroduction = briefintroduction == null ? null : briefintroduction.trim();
    }

    public Integer getIdentify() {
        return Identify;
    }

    public void setIdentify(Integer identify) {
        this.Identify = identify;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", UserID=").append(UserID);
        sb.append(", Password=").append(Password);
        sb.append(", Name=").append(Name);
        sb.append(", Sex=").append(Sex);
        sb.append(", UserImagePath=").append(UserImagePath);
        sb.append(", Email=").append(Email);
        sb.append(", BriefIntroduction=").append(BriefIntroduction);
        sb.append(", Identify=").append(Identify);
        sb.append(", SerialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}