package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.models.ScholarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScholarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int countByExample(ScholarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int deleteByExample(ScholarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer scholarid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int insert(Scholar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int insertSelective(Scholar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    List<Scholar> selectByExample(ScholarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    Scholar selectByPrimaryKey(Integer scholarid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Scholar record, @Param("example") ScholarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Scholar record, @Param("example") ScholarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Scholar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Scholar
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Scholar record);

    Scholar selectByUID(@Param("target_user") Integer uid);
}