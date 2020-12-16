package cn.edu.buaa.scholarshipserver.models;

import java.util.Date;

public class Collect_Project {

    Long ProjectId;
    Integer WatcherID;
    Date CollectDatetime;

    public Collect_Project(Long projectId, Integer watcherID, Date collectDatetime) {
        ProjectId = projectId;
        WatcherID = watcherID;
        CollectDatetime = collectDatetime;
    }

    public Long getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Long projectId) {
        ProjectId = projectId;
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
