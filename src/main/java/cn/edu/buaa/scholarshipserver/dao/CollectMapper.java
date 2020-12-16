package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Collect_Article;
import cn.edu.buaa.scholarshipserver.models.Collect_Patent;
import cn.edu.buaa.scholarshipserver.models.Collect_Project;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectMapper {

    @Select("select * from Collect_Article where WatcherID=#{userId} and PaperId=#{paperId}")
    Collect_Article getCollectPaper(Integer userId, Long paperId);

    @Select("select * from Collect_Patent where WatcherID=#{userId} and PatentId=#{paperId}")
    Collect_Patent getCollectPatent(Integer userId, Long paperId);

    @Select("select * from Collect_Project where WatcherID=#{userId} and ProjectId=#{paperId}")
    Collect_Project getCollectProject(Integer userId, Long paperId);

    @Select("select * from Collect_Article where WatcherID=#{userId}")
    List<Collect_Article> getAllCollectPaper(Integer userId, Long paperId);

    @Select("select * from Collect_Patent where WatcherID=#{userId}")
    List<Collect_Patent> getAllCollectPatent(Integer userId, Long paperId);

    @Select("select * from Collect_Project where WatcherID=#{userId}")
    List<Collect_Project> getAllCollectProject(Integer userId, Long paperId);

    @Delete("delete from Collect_Article where WatcherID=#{userId} and PaperId=#{paperId}")
    int cancelCollectPaper(Integer userId, Long paperId);

    @Delete("delete from Collect_Patent where WatcherID=#{userId} and PatentId=#{paperId}")
    int cancelCollectPatent(Integer userId, Long paperId);

    @Delete("delete from Collect_Project where WatcherID=#{userId} and ProjectId=#{paperId}")
    int cancelCollectProject(Integer userId, Long paperId);

    @Insert("insert into Collect_Article(PaperId,WatcherID,CollectDatetime) values(#{PaperId},#{WatcherID},#{CollectDatetime})")
    int collectPaper(Collect_Article collect_article);

    @Insert("insert into Collect_Patent(PatentId,WatcherID,CollectDatetime) values(#{PatentId},#{WatcherID},#{CollectDatetime})")
    int collectPatent(Collect_Patent collect_patent);

    @Insert("insert into Collect_Project(ProjectId,WatcherID,CollectDatetime) values(#{ProjectId},#{WatcherID},#{CollectDatetime})")
    int collectProject(Collect_Project collect_project);

}
