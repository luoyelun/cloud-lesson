package icu.thyself.cloudlesson.mapper;

import icu.thyself.cloudlesson.model.Post;
import icu.thyself.cloudlesson.model.PostExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    long countByExample(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int deleteByExample(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int insert(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int insertSelective(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    List<Post> selectByExampleWithBLOBs(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    List<Post> selectByExample(PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    Post selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int updateByExampleSelective(@Param("record") Post record, @Param("example") PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") Post record, @Param("example") PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int updateByExample(@Param("record") Post record, @Param("example") PostExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int updateByPrimaryKeySelective(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(Post record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table post
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    int updateByPrimaryKey(Post record);
}