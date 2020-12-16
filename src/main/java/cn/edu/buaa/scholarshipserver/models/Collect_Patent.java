package cn.edu.buaa.scholarshipserver.models;

import java.util.Date;

public class Collect_Patent {

    Long PatentId;
    Integer WatcherID;
    Date CollectDatetime;

    public Collect_Patent(Long patentId, Integer watcherID, Date collectDatetime) {
        PatentId = patentId;
        WatcherID = watcherID;
        CollectDatetime = collectDatetime;
    }

    public Long getPatentId() {
        return PatentId;
    }

    public void setPatentId(Long patentId) {
        PatentId = patentId;
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
