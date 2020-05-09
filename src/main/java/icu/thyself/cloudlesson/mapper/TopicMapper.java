package icu.thyself.cloudlesson.mapper;

import icu.thyself.cloudlesson.model.Topic;
import icu.thyself.cloudlesson.model.TopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    long countByExample(TopicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int deleteByExample(TopicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int insert(Topic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int insertSelective(Topic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    List<Topic> selectByExampleWithBLOBs(TopicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    List<Topic> selectByExample(TopicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    Topic selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int updateByExampleSelective(@Param("record") Topic record, @Param("example") TopicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Topic record, @Param("example") TopicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int updateByPrimaryKeySelective(Topic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Topic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table topic
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    int updateByPrimaryKey(Topic record);
}