package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Message;
import cn.edu.buaa.scholarshipserver.models.MessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int countByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int deleteByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer msgid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int insert(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int insertSelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    List<Message> selectByExample(MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    Message selectByPrimaryKey(Integer msgid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Message
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Message record);
}