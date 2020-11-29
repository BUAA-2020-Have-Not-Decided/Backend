package cn.edu.buaa.scholarshipserver.dao;

import cn.edu.buaa.scholarshipserver.models.Fields;
import cn.edu.buaa.scholarshipserver.models.FieldsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FieldsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int countByExample(FieldsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int deleteByExample(FieldsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long fieldsid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int insert(Fields record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int insertSelective(Fields record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    List<Fields> selectByExample(FieldsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    Fields selectByPrimaryKey(Long fieldsid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Fields record, @Param("example") FieldsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Fields record, @Param("example") FieldsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Fields record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Fields
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Fields record);
}