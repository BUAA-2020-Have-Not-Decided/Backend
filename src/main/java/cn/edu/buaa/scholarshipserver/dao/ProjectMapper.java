package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.models.ProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int countByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int deleteByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long projectid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int insert(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int insertSelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    List<Project> selectByExample(ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    Project selectByPrimaryKey(Long projectid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Project
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Project record);
}