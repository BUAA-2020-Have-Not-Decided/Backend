package cn.edu.buaa.scholarshipserver.models;

import java.io.Serializable;
import java.util.Date;

public class Patent implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.PatentId
     *
     * @mbggenerated
     */
    private Long patentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Abtract
     *
     * @mbggenerated
     */
    private String abtract;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.ApplicationDate
     *
     * @mbggenerated
     */
    private Date applicationdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Agency
     *
     * @mbggenerated
     */
    private String agency;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.ApplicationNumber
     *
     * @mbggenerated
     */
    private String applicationnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Agent
     *
     * @mbggenerated
     */
    private String agent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Province
     *
     * @mbggenerated
     */
    private String province;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Location
     *
     * @mbggenerated
     */
    private String location;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.ClassificationNumber
     *
     * @mbggenerated
     */
    private String classificationnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.MainClassificationNumber
     *
     * @mbggenerated
     */
    private String mainclassificationnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Inventor
     *
     * @mbggenerated
     */
    private String inventor;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.PublishDate
     *
     * @mbggenerated
     */
    private Date publishdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Applicant
     *
     * @mbggenerated
     */
    private String applicant;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.CurrentObligee
     *
     * @mbggenerated
     */
    private String currentobligee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.PublishNumber
     *
     * @mbggenerated
     */
    private String publishnumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.Title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Patent.State
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Patent
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.PatentId
     *
     * @return the value of Patent.PatentId
     *
     * @mbggenerated
     */
    public Long getPatentid() {
        return patentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.PatentId
     *
     * @param patentid the value for Patent.PatentId
     *
     * @mbggenerated
     */
    public void setPatentid(Long patentid) {
        this.patentid = patentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Abtract
     *
     * @return the value of Patent.Abtract
     *
     * @mbggenerated
     */
    public String getAbtract() {
        return abtract;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Abtract
     *
     * @param abtract the value for Patent.Abtract
     *
     * @mbggenerated
     */
    public void setAbtract(String abtract) {
        this.abtract = abtract == null ? null : abtract.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.ApplicationDate
     *
     * @return the value of Patent.ApplicationDate
     *
     * @mbggenerated
     */
    public Date getApplicationdate() {
        return applicationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.ApplicationDate
     *
     * @param applicationdate the value for Patent.ApplicationDate
     *
     * @mbggenerated
     */
    public void setApplicationdate(Date applicationdate) {
        this.applicationdate = applicationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Agency
     *
     * @return the value of Patent.Agency
     *
     * @mbggenerated
     */
    public String getAgency() {
        return agency;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Agency
     *
     * @param agency the value for Patent.Agency
     *
     * @mbggenerated
     */
    public void setAgency(String agency) {
        this.agency = agency == null ? null : agency.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.ApplicationNumber
     *
     * @return the value of Patent.ApplicationNumber
     *
     * @mbggenerated
     */
    public String getApplicationnumber() {
        return applicationnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.ApplicationNumber
     *
     * @param applicationnumber the value for Patent.ApplicationNumber
     *
     * @mbggenerated
     */
    public void setApplicationnumber(String applicationnumber) {
        this.applicationnumber = applicationnumber == null ? null : applicationnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Agent
     *
     * @return the value of Patent.Agent
     *
     * @mbggenerated
     */
    public String getAgent() {
        return agent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Agent
     *
     * @param agent the value for Patent.Agent
     *
     * @mbggenerated
     */
    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Content
     *
     * @return the value of Patent.Content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Content
     *
     * @param content the value for Patent.Content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Province
     *
     * @return the value of Patent.Province
     *
     * @mbggenerated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Province
     *
     * @param province the value for Patent.Province
     *
     * @mbggenerated
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Location
     *
     * @return the value of Patent.Location
     *
     * @mbggenerated
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Location
     *
     * @param location the value for Patent.Location
     *
     * @mbggenerated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.ClassificationNumber
     *
     * @return the value of Patent.ClassificationNumber
     *
     * @mbggenerated
     */
    public String getClassificationnumber() {
        return classificationnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.ClassificationNumber
     *
     * @param classificationnumber the value for Patent.ClassificationNumber
     *
     * @mbggenerated
     */
    public void setClassificationnumber(String classificationnumber) {
        this.classificationnumber = classificationnumber == null ? null : classificationnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.MainClassificationNumber
     *
     * @return the value of Patent.MainClassificationNumber
     *
     * @mbggenerated
     */
    public String getMainclassificationnumber() {
        return mainclassificationnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.MainClassificationNumber
     *
     * @param mainclassificationnumber the value for Patent.MainClassificationNumber
     *
     * @mbggenerated
     */
    public void setMainclassificationnumber(String mainclassificationnumber) {
        this.mainclassificationnumber = mainclassificationnumber == null ? null : mainclassificationnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Inventor
     *
     * @return the value of Patent.Inventor
     *
     * @mbggenerated
     */
    public String getInventor() {
        return inventor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Inventor
     *
     * @param inventor the value for Patent.Inventor
     *
     * @mbggenerated
     */
    public void setInventor(String inventor) {
        this.inventor = inventor == null ? null : inventor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.PublishDate
     *
     * @return the value of Patent.PublishDate
     *
     * @mbggenerated
     */
    public Date getPublishdate() {
        return publishdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.PublishDate
     *
     * @param publishdate the value for Patent.PublishDate
     *
     * @mbggenerated
     */
    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Applicant
     *
     * @return the value of Patent.Applicant
     *
     * @mbggenerated
     */
    public String getApplicant() {
        return applicant;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Applicant
     *
     * @param applicant the value for Patent.Applicant
     *
     * @mbggenerated
     */
    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.CurrentObligee
     *
     * @return the value of Patent.CurrentObligee
     *
     * @mbggenerated
     */
    public String getCurrentobligee() {
        return currentobligee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.CurrentObligee
     *
     * @param currentobligee the value for Patent.CurrentObligee
     *
     * @mbggenerated
     */
    public void setCurrentobligee(String currentobligee) {
        this.currentobligee = currentobligee == null ? null : currentobligee.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.PublishNumber
     *
     * @return the value of Patent.PublishNumber
     *
     * @mbggenerated
     */
    public String getPublishnumber() {
        return publishnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.PublishNumber
     *
     * @param publishnumber the value for Patent.PublishNumber
     *
     * @mbggenerated
     */
    public void setPublishnumber(String publishnumber) {
        this.publishnumber = publishnumber == null ? null : publishnumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.Title
     *
     * @return the value of Patent.Title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.Title
     *
     * @param title the value for Patent.Title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Patent.State
     *
     * @return the value of Patent.State
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Patent.State
     *
     * @param state the value for Patent.State
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Patent
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", patentid=").append(patentid);
        sb.append(", abtract=").append(abtract);
        sb.append(", applicationdate=").append(applicationdate);
        sb.append(", agency=").append(agency);
        sb.append(", applicationnumber=").append(applicationnumber);
        sb.append(", agent=").append(agent);
        sb.append(", content=").append(content);
        sb.append(", province=").append(province);
        sb.append(", location=").append(location);
        sb.append(", classificationnumber=").append(classificationnumber);
        sb.append(", mainclassificationnumber=").append(mainclassificationnumber);
        sb.append(", inventor=").append(inventor);
        sb.append(", publishdate=").append(publishdate);
        sb.append(", applicant=").append(applicant);
        sb.append(", currentobligee=").append(currentobligee);
        sb.append(", publishnumber=").append(publishnumber);
        sb.append(", title=").append(title);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}