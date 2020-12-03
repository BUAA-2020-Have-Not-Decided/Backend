package cn.edu.buaa.scholarshipserver.models;

import java.io.Serializable;
import java.util.Date;

public class Scholar implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.ScholarId
     *
     * @mbggenerated
     */
    private Integer scholarid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.InstitutionId
     *
     * @mbggenerated
     */
    private Integer institutionid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.EnglishName
     *
     * @mbggenerated
     */
    private String englishname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Organization
     *
     * @mbggenerated
     */
    private String organization;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Fans
     *
     * @mbggenerated
     */
    private Integer fans;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.PersonalPage
     *
     * @mbggenerated
     */
    private String personalpage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Introduction
     *
     * @mbggenerated
     */
    private String introduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Papers
     *
     * @mbggenerated
     */
    private Integer papers;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Citations
     *
     * @mbggenerated
     */
    private Integer citations;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.HIndex
     *
     * @mbggenerated
     */
    private Integer hindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.GIndex
     *
     * @mbggenerated
     */
    private Integer gindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Sociability
     *
     * @mbggenerated
     */
    private Integer sociability;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Diversity
     *
     * @mbggenerated
     */
    private Integer diversity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.Activity
     *
     * @mbggenerated
     */
    private Integer activity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.AvatarUrl
     *
     * @mbggenerated
     */
    private String avatarurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Scholar.LastKnownTime
     *
     * @mbggenerated
     */
    private Date lastknowntime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.ScholarId
     *
     * @return the value of Scholar.ScholarId
     *
     * @mbggenerated
     */
    public Integer getScholarid() {
        return scholarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.ScholarId
     *
     * @param scholarid the value for Scholar.ScholarId
     *
     * @mbggenerated
     */
    public void setScholarid(Integer scholarid) {
        this.scholarid = scholarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.InstitutionId
     *
     * @return the value of Scholar.InstitutionId
     *
     * @mbggenerated
     */
    public Integer getInstitutionid() {
        return institutionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.InstitutionId
     *
     * @param institutionid the value for Scholar.InstitutionId
     *
     * @mbggenerated
     */
    public void setInstitutionid(Integer institutionid) {
        this.institutionid = institutionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.EnglishName
     *
     * @return the value of Scholar.EnglishName
     *
     * @mbggenerated
     */
    public String getEnglishname() {
        return englishname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.EnglishName
     *
     * @param englishname the value for Scholar.EnglishName
     *
     * @mbggenerated
     */
    public void setEnglishname(String englishname) {
        this.englishname = englishname == null ? null : englishname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Name
     *
     * @return the value of Scholar.Name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Name
     *
     * @param name the value for Scholar.Name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Title
     *
     * @return the value of Scholar.Title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Title
     *
     * @param title the value for Scholar.Title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Organization
     *
     * @return the value of Scholar.Organization
     *
     * @mbggenerated
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Organization
     *
     * @param organization the value for Scholar.Organization
     *
     * @mbggenerated
     */
    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Email
     *
     * @return the value of Scholar.Email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Email
     *
     * @param email the value for Scholar.Email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Phone
     *
     * @return the value of Scholar.Phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Phone
     *
     * @param phone the value for Scholar.Phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Fans
     *
     * @return the value of Scholar.Fans
     *
     * @mbggenerated
     */
    public Integer getFans() {
        return fans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Fans
     *
     * @param fans the value for Scholar.Fans
     *
     * @mbggenerated
     */
    public void setFans(Integer fans) {
        this.fans = fans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.PersonalPage
     *
     * @return the value of Scholar.PersonalPage
     *
     * @mbggenerated
     */
    public String getPersonalpage() {
        return personalpage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.PersonalPage
     *
     * @param personalpage the value for Scholar.PersonalPage
     *
     * @mbggenerated
     */
    public void setPersonalpage(String personalpage) {
        this.personalpage = personalpage == null ? null : personalpage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Introduction
     *
     * @return the value of Scholar.Introduction
     *
     * @mbggenerated
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Introduction
     *
     * @param introduction the value for Scholar.Introduction
     *
     * @mbggenerated
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Papers
     *
     * @return the value of Scholar.Papers
     *
     * @mbggenerated
     */
    public Integer getPapers() {
        return papers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Papers
     *
     * @param papers the value for Scholar.Papers
     *
     * @mbggenerated
     */
    public void setPapers(Integer papers) {
        this.papers = papers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Citations
     *
     * @return the value of Scholar.Citations
     *
     * @mbggenerated
     */
    public Integer getCitations() {
        return citations;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Citations
     *
     * @param citations the value for Scholar.Citations
     *
     * @mbggenerated
     */
    public void setCitations(Integer citations) {
        this.citations = citations;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.HIndex
     *
     * @return the value of Scholar.HIndex
     *
     * @mbggenerated
     */
    public Integer getHindex() {
        return hindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.HIndex
     *
     * @param hindex the value for Scholar.HIndex
     *
     * @mbggenerated
     */
    public void setHindex(Integer hindex) {
        this.hindex = hindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.GIndex
     *
     * @return the value of Scholar.GIndex
     *
     * @mbggenerated
     */
    public Integer getGindex() {
        return gindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.GIndex
     *
     * @param gindex the value for Scholar.GIndex
     *
     * @mbggenerated
     */
    public void setGindex(Integer gindex) {
        this.gindex = gindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Sociability
     *
     * @return the value of Scholar.Sociability
     *
     * @mbggenerated
     */
    public Integer getSociability() {
        return sociability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Sociability
     *
     * @param sociability the value for Scholar.Sociability
     *
     * @mbggenerated
     */
    public void setSociability(Integer sociability) {
        this.sociability = sociability;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Diversity
     *
     * @return the value of Scholar.Diversity
     *
     * @mbggenerated
     */
    public Integer getDiversity() {
        return diversity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Diversity
     *
     * @param diversity the value for Scholar.Diversity
     *
     * @mbggenerated
     */
    public void setDiversity(Integer diversity) {
        this.diversity = diversity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.Activity
     *
     * @return the value of Scholar.Activity
     *
     * @mbggenerated
     */
    public Integer getActivity() {
        return activity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.Activity
     *
     * @param activity the value for Scholar.Activity
     *
     * @mbggenerated
     */
    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.AvatarUrl
     *
     * @return the value of Scholar.AvatarUrl
     *
     * @mbggenerated
     */
    public String getAvatarurl() {
        return avatarurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.AvatarUrl
     *
     * @param avatarurl the value for Scholar.AvatarUrl
     *
     * @mbggenerated
     */
    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl == null ? null : avatarurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Scholar.LastKnownTime
     *
     * @return the value of Scholar.LastKnownTime
     *
     * @mbggenerated
     */
    public Date getLastknowntime() {
        return lastknowntime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Scholar.LastKnownTime
     *
     * @param lastknowntime the value for Scholar.LastKnownTime
     *
     * @mbggenerated
     */
    public void setLastknowntime(Date lastknowntime) {
        this.lastknowntime = lastknowntime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
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