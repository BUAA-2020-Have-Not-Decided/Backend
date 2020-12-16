package cn.edu.buaa.scholarshipserver.models;

import java.util.Date;

public class Collect_Article {

    Long PaperId;
    Integer WatcherID;
    Date CollectDatetime;

    public Collect_Article(Long paperId, Integer watcherID, Date collectDatetime) {
        PaperId = paperId;
        WatcherID = watcherID;
        CollectDatetime = collectDatetime;
    }

    public Long getPaperId() {
        return PaperId;
    }

    public void setPaperId(Long paperId) {
        PaperId = paperId;
    }

    public Integer getWatcherID() {
        return WatcherID;
    }

    public void setWatcherID(Integer watcherID) {
        WatcherID = watcherID;
    }

    public Date getCollectDatetime() {
        return CollectDatetime;
    }

    public void setCollectDatetime(Date collectDatetime) {
        CollectDatetime = collectDatetime;
    }

}
