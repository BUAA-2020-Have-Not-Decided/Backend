package cn.edu.buaa.scholarshipserver.models;

import java.io.Serializable;

public class Institution implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Institution.InstitutionId
     *
     * @mbggenerated
     */
    private Integer institutionid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Institution.InstitutionName
     *
     * @mbggenerated
     */
    private String institutionname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Institution.NatureIndex
     *
     * @mbggenerated
     */
    private Float natureindex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Institution
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Institution.InstitutionId
     *
     * @return the value of Institution.InstitutionId
     *
     * @mbggenerated
     */
    public Integer getInstitutionid() {
        return institutionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Institution.InstitutionId
     *
     * @param institutionid the value for Institution.InstitutionId
     *
     * @mbggenerated
     */
    public void setInstitutionid(Integer institutionid) {
        this.institutionid = institutionid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Institution.InstitutionName
     *
     * @return the value of Institution.InstitutionName
     *
     * @mbggenerated
     */
    public String getInstitutionname() {
        return institutionname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Institution.InstitutionName
     *
     * @param institutionname the value for Institution.InstitutionName
     *
     * @mbggenerated
     */
    public void setInstitutionname(String institutionname) {
        this.institutionname = institutionname == null ? null : institutionname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Institution.NatureIndex
     *
     * @return the value of Institution.NatureIndex
     *
     * @mbggenerated
     */
    public Float getNatureindex() {
        return natureindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Institution.NatureIndex
     *
     * @param natureindex the value for Institution.NatureIndex
     *
     * @mbggenerated
     */
    public void setNatureindex(Float natureindex) {
        this.natureindex = natureindex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Institution
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", institutionid=").append(institutionid);
        sb.append(", institutionname=").append(institutionname);
        sb.append(", natureindex=").append(natureindex);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}