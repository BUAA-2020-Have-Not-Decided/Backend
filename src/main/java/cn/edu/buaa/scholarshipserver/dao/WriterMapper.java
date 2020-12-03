package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Writer;
import cn.edu.buaa.scholarshipserver.models.WriterExample;
import cn.edu.buaa.scholarshipserver.models.WriterKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WriterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int countByExample(WriterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int deleteByExample(WriterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(WriterKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int insert(Writer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int insertSelective(Writer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    List<Writer> selectByExample(WriterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    Writer selectByPrimaryKey(WriterKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Writer record, @Param("example") WriterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Writer record, @Param("example") WriterExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Writer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Writer
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Writer record);
}