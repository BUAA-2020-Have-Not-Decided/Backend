package cn.edu.buaa.scholarshipserver.models;

import java.io.Serializable;
import java.util.Date;

public class Paper implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.PaperId
     *
     * @mbggenerated
     */
    private Long paperid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.Doi
     *
     * @mbggenerated
     */
    private String doi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.DocType
     *
     * @mbggenerated
     */
    private String doctype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.PaperTitle
     *
     * @mbggenerated
     */
    private String papertitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.Abtract
     *
     * @mbggenerated
     */
    private String abtract;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.CitationCount
     *
     * @mbggenerated
     */
    private Long citationcount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.Date
     *
     * @mbggenerated
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.Journal
     *
     * @mbggenerated
     */
    private String journal;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.Conference
     *
     * @mbggenerated
     */
    private String conference;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.Volume
     *
     * @mbggenerated
     */
    private String volume;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.Issue
     *
     * @mbggenerated
     */
    private String issue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.FirstPage
     *
     * @mbggenerated
     */
    private String firstpage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.LastPage
     *
     * @mbggenerated
     */
    private String lastpage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Paper.SourceUrl
     *
     * @mbggenerated
     */
    private String sourceurl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table Paper
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.PaperId
     *
     * @return the value of Paper.PaperId
     *
     * @mbggenerated
     */
    public Long getPaperid() {
        return paperid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.PaperId
     *
     * @param paperid the value for Paper.PaperId
     *
     * @mbggenerated
     */
    public void setPaperid(Long paperid) {
        this.paperid = paperid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.Doi
     *
     * @return the value of Paper.Doi
     *
     * @mbggenerated
     */
    public String getDoi() {
        return doi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.Doi
     *
     * @param doi the value for Paper.Doi
     *
     * @mbggenerated
     */
    public void setDoi(String doi) {
        this.doi = doi == null ? null : doi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.DocType
     *
     * @return the value of Paper.DocType
     *
     * @mbggenerated
     */
    public String getDoctype() {
        return doctype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.DocType
     *
     * @param doctype the value for Paper.DocType
     *
     * @mbggenerated
     */
    public void setDoctype(String doctype) {
        this.doctype = doctype == null ? null : doctype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.PaperTitle
     *
     * @return the value of Paper.PaperTitle
     *
     * @mbggenerated
     */
    public String getPapertitle() {
        return papertitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.PaperTitle
     *
     * @param papertitle the value for Paper.PaperTitle
     *
     * @mbggenerated
     */
    public void setPapertitle(String papertitle) {
        this.papertitle = papertitle == null ? null : papertitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.Abtract
     *
     * @return the value of Paper.Abtract
     *
     * @mbggenerated
     */
    public String getAbtract() {
        return abtract;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.Abtract
     *
     * @param abtract the value for Paper.Abtract
     *
     * @mbggenerated
     */
    public void setAbtract(String abtract) {
        this.abtract = abtract == null ? null : abtract.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.CitationCount
     *
     * @return the value of Paper.CitationCount
     *
     * @mbggenerated
     */
    public Long getCitationcount() {
        return citationcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.CitationCount
     *
     * @param citationcount the value for Paper.CitationCount
     *
     * @mbggenerated
     */
    public void setCitationcount(Long citationcount) {
        this.citationcount = citationcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.Date
     *
     * @return the value of Paper.Date
     *
     * @mbggenerated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.Date
     *
     * @param date the value for Paper.Date
     *
     * @mbggenerated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.Journal
     *
     * @return the value of Paper.Journal
     *
     * @mbggenerated
     */
    public String getJournal() {
        return journal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.Journal
     *
     * @param journal the value for Paper.Journal
     *
     * @mbggenerated
     */
    public void setJournal(String journal) {
        this.journal = journal == null ? null : journal.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.Conference
     *
     * @return the value of Paper.Conference
     *
     * @mbggenerated
     */
    public String getConference() {
        return conference;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.Conference
     *
     * @param conference the value for Paper.Conference
     *
     * @mbggenerated
     */
    public void setConference(String conference) {
        this.conference = conference == null ? null : conference.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.Volume
     *
     * @return the value of Paper.Volume
     *
     * @mbggenerated
     */
    public String getVolume() {
        return volume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.Volume
     *
     * @param volume the value for Paper.Volume
     *
     * @mbggenerated
     */
    public void setVolume(String volume) {
        this.volume = volume == null ? null : volume.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.Issue
     *
     * @return the value of Paper.Issue
     *
     * @mbggenerated
     */
    public String getIssue() {
        return issue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.Issue
     *
     * @param issue the value for Paper.Issue
     *
     * @mbggenerated
     */
    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.FirstPage
     *
     * @return the value of Paper.FirstPage
     *
     * @mbggenerated
     */
    public String getFirstpage() {
        return firstpage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.FirstPage
     *
     * @param firstpage the value for Paper.FirstPage
     *
     * @mbggenerated
     */
    public void setFirstpage(String firstpage) {
        this.firstpage = firstpage == null ? null : firstpage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.LastPage
     *
     * @return the value of Paper.LastPage
     *
     * @mbggenerated
     */
    public String getLastpage() {
        return lastpage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.LastPage
     *
     * @param lastpage the value for Paper.LastPage
     *
     * @mbggenerated
     */
    public void setLastpage(String lastpage) {
        this.lastpage = lastpage == null ? null : lastpage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Paper.SourceUrl
     *
     * @return the value of Paper.SourceUrl
     *
     * @mbggenerated
     */
    public String getSourceurl() {
        return sourceurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Paper.SourceUrl
     *
     * @param sourceurl the value for Paper.SourceUrl
     *
     * @mbggenerated
     */
    public void setSourceurl(String sourceurl) {
        this.sourceurl = sourceurl == null ? null : sourceurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Paper
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", paperid=").append(paperid);
        sb.append(", doi=").append(doi);
        sb.append(", doctype=").append(doctype);
        sb.append(", papertitle=").append(papertitle);
        sb.append(", abtract=").append(abtract);
        sb.append(", citationcount=").append(citationcount);
        sb.append(", date=").append(date);
        sb.append(", journal=").append(journal);
        sb.append(", conference=").append(conference);
        sb.append(", volume=").append(volume);
        sb.append(", issue=").append(issue);
        sb.append(", firstpage=").append(firstpage);
        sb.append(", lastpage=").append(lastpage);
        sb.append(", sourceurl=").append(sourceurl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}