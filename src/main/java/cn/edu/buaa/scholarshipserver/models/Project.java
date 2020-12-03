package cn.edu.buaa.scholarshipserver.models;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.ProjectId
     *
     * @mbggenerated
     */
    private Long projectid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.Organization
     *
     * @mbggenerated
     */
    private String organization;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.FundProjectCode
     *
     * @mbggenerated
     */
    private String fundprojectcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.Source
     *
     * @mbggenerated
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.Doi
     *
     * @mbggenerated
     */
    private String doi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.FieldName
     *
     * @mbggenerated
     */
    private String fieldname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.DoiUrl
     *
     * @mbggenerated
     */
    private String doiurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.ZhAbstract
     *
     * @mbggenerated
     */
    private String zhabstract;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.FundProject
     *
     * @mbggenerated
     */
    private String fundproject;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.ProjectAuthors
     *
     * @mbggenerated
     */
    private String projectauthors;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.FieldCode
     *
     * @mbggenerated
     */
    private String fieldcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.OrganizationId
     *
     * @mbggenerated
     */
    private String organizationid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.SupportTypeName
     *
     * @mbggenerated
     */
    private String supporttypename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.ChineseTitle
     *
     * @mbggenerated
     */
    private String chinesetitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.PublishDate
     *
     * @mbggenerated
     */
    private Date publishdate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.FundProjectNo
     *
     * @mbggenerated
     */
    private String fundprojectno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.AchievementID
     *
     * @mbggenerated
     */
    private String achievementid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.Journal
     *
     * @mbggenerated
     */
    private String journal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.ProductType
     *
     * @mbggenerated
     */
    private String producttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.ZhKeyword
     *
     * @mbggenerated
     */
    private String zhkeyword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Project.SupportType
     *
     * @mbggenerated
     */
    private String supporttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Project
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.ProjectId
     *
     * @return the value of Project.ProjectId
     *
     * @mbggenerated
     */
    public Long getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.ProjectId
     *
     * @param projectid the value for Project.ProjectId
     *
     * @mbggenerated
     */
    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.Organization
     *
     * @return the value of Project.Organization
     *
     * @mbggenerated
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.Organization
     *
     * @param organization the value for Project.Organization
     *
     * @mbggenerated
     */
    public void setOrganization(String organization) {
        this.organization = organization == null ? null : organization.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.FundProjectCode
     *
     * @return the value of Project.FundProjectCode
     *
     * @mbggenerated
     */
    public String getFundprojectcode() {
        return fundprojectcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.FundProjectCode
     *
     * @param fundprojectcode the value for Project.FundProjectCode
     *
     * @mbggenerated
     */
    public void setFundprojectcode(String fundprojectcode) {
        this.fundprojectcode = fundprojectcode == null ? null : fundprojectcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.Source
     *
     * @return the value of Project.Source
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.Source
     *
     * @param source the value for Project.Source
     *
     * @mbggenerated
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.Doi
     *
     * @return the value of Project.Doi
     *
     * @mbggenerated
     */
    public String getDoi() {
        return doi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.Doi
     *
     * @param doi the value for Project.Doi
     *
     * @mbggenerated
     */
    public void setDoi(String doi) {
        this.doi = doi == null ? null : doi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.FieldName
     *
     * @return the value of Project.FieldName
     *
     * @mbggenerated
     */
    public String getFieldname() {
        return fieldname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.FieldName
     *
     * @param fieldname the value for Project.FieldName
     *
     * @mbggenerated
     */
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname == null ? null : fieldname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.DoiUrl
     *
     * @return the value of Project.DoiUrl
     *
     * @mbggenerated
     */
    public String getDoiurl() {
        return doiurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.DoiUrl
     *
     * @param doiurl the value for Project.DoiUrl
     *
     * @mbggenerated
     */
    public void setDoiurl(String doiurl) {
        this.doiurl = doiurl == null ? null : doiurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.ZhAbstract
     *
     * @return the value of Project.ZhAbstract
     *
     * @mbggenerated
     */
    public String getZhabstract() {
        return zhabstract;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.ZhAbstract
     *
     * @param zhabstract the value for Project.ZhAbstract
     *
     * @mbggenerated
     */
    public void setZhabstract(String zhabstract) {
        this.zhabstract = zhabstract == null ? null : zhabstract.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.FundProject
     *
     * @return the value of Project.FundProject
     *
     * @mbggenerated
     */
    public String getFundproject() {
        return fundproject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.FundProject
     *
     * @param fundproject the value for Project.FundProject
     *
     * @mbggenerated
     */
    public void setFundproject(String fundproject) {
        this.fundproject = fundproject == null ? null : fundproject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.ProjectAuthors
     *
     * @return the value of Project.ProjectAuthors
     *
     * @mbggenerated
     */
    public String getProjectauthors() {
        return projectauthors;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.ProjectAuthors
     *
     * @param projectauthors the value for Project.ProjectAuthors
     *
     * @mbggenerated
     */
    public void setProjectauthors(String projectauthors) {
        this.projectauthors = projectauthors == null ? null : projectauthors.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.FieldCode
     *
     * @return the value of Project.FieldCode
     *
     * @mbggenerated
     */
    public String getFieldcode() {
        return fieldcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.FieldCode
     *
     * @param fieldcode the value for Project.FieldCode
     *
     * @mbggenerated
     */
    public void setFieldcode(String fieldcode) {
        this.fieldcode = fieldcode == null ? null : fieldcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.OrganizationId
     *
     * @return the value of Project.OrganizationId
     *
     * @mbggenerated
     */
    public String getOrganizationid() {
        return organizationid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.OrganizationId
     *
     * @param organizationid the value for Project.OrganizationId
     *
     * @mbggenerated
     */
    public void setOrganizationid(String organizationid) {
        this.organizationid = organizationid == null ? null : organizationid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.SupportTypeName
     *
     * @return the value of Project.SupportTypeName
     *
     * @mbggenerated
     */
    public String getSupporttypename() {
        return supporttypename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.SupportTypeName
     *
     * @param supporttypename the value for Project.SupportTypeName
     *
     * @mbggenerated
     */
    public void setSupporttypename(String supporttypename) {
        this.supporttypename = supporttypename == null ? null : supporttypename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.ChineseTitle
     *
     * @return the value of Project.ChineseTitle
     *
     * @mbggenerated
     */
    public String getChinesetitle() {
        return chinesetitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.ChineseTitle
     *
     * @param chinesetitle the value for Project.ChineseTitle
     *
     * @mbggenerated
     */
    public void setChinesetitle(String chinesetitle) {
        this.chinesetitle = chinesetitle == null ? null : chinesetitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.PublishDate
     *
     * @return the value of Project.PublishDate
     *
     * @mbggenerated
     */
    public Date getPublishdate() {
        return publishdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.PublishDate
     *
     * @param publishdate the value for Project.PublishDate
     *
     * @mbggenerated
     */
    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.FundProjectNo
     *
     * @return the value of Project.FundProjectNo
     *
     * @mbggenerated
     */
    public String getFundprojectno() {
        return fundprojectno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.FundProjectNo
     *
     * @param fundprojectno the value for Project.FundProjectNo
     *
     * @mbggenerated
     */
    public void setFundprojectno(String fundprojectno) {
        this.fundprojectno = fundprojectno == null ? null : fundprojectno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.AchievementID
     *
     * @return the value of Project.AchievementID
     *
     * @mbggenerated
     */
    public String getAchievementid() {
        return achievementid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.AchievementID
     *
     * @param achievementid the value for Project.AchievementID
     *
     * @mbggenerated
     */
    public void setAchievementid(String achievementid) {
        this.achievementid = achievementid == null ? null : achievementid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.Journal
     *
     * @return the value of Project.Journal
     *
     * @mbggenerated
     */
    public String getJournal() {
        return journal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.Journal
     *
     * @param journal the value for Project.Journal
     *
     * @mbggenerated
     */
    public void setJournal(String journal) {
        this.journal = journal == null ? null : journal.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.ProductType
     *
     * @return the value of Project.ProductType
     *
     * @mbggenerated
     */
    public String getProducttype() {
        return producttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.ProductType
     *
     * @param producttype the value for Project.ProductType
     *
     * @mbggenerated
     */
    public void setProducttype(String producttype) {
        this.producttype = producttype == null ? null : producttype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.ZhKeyword
     *
     * @return the value of Project.ZhKeyword
     *
     * @mbggenerated
     */
    public String getZhkeyword() {
        return zhkeyword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.ZhKeyword
     *
     * @param zhkeyword the value for Project.ZhKeyword
     *
     * @mbggenerated
     */
    public void setZhkeyword(String zhkeyword) {
        this.zhkeyword = zhkeyword == null ? null : zhkeyword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.SupportType
     *
     * @return the value of Project.SupportType
     *
     * @mbggenerated
     */
    public String getSupporttype() {
        return supporttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.SupportType
     *
     * @param supporttype the value for Project.SupportType
     *
     * @mbggenerated
     */
    public void setSupporttype(String supporttype) {
        this.supporttype = supporttype == null ? null : supporttype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", projectid=").append(projectid);
        sb.append(", organization=").append(organization);
        sb.append(", fundprojectcode=").append(fundprojectcode);
        sb.append(", source=").append(source);
        sb.append(", doi=").append(doi);
        sb.append(", fieldname=").append(fieldname);
        sb.append(", doiurl=").append(doiurl);
        sb.append(", zhabstract=").append(zhabstract);
        sb.append(", fundproject=").append(fundproject);
        sb.append(", projectauthors=").append(projectauthors);
        sb.append(", fieldcode=").append(fieldcode);
        sb.append(", organizationid=").append(organizationid);
        sb.append(", supporttypename=").append(supporttypename);
        sb.append(", chinesetitle=").append(chinesetitle);
        sb.append(", publishdate=").append(publishdate);
        sb.append(", fundprojectno=").append(fundprojectno);
        sb.append(", achievementid=").append(achievementid);
        sb.append(", journal=").append(journal);
        sb.append(", producttype=").append(producttype);
        sb.append(", zhkeyword=").append(zhkeyword);
        sb.append(", supporttype=").append(supporttype);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}