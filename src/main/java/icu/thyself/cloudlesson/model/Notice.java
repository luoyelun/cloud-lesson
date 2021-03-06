package icu.thyself.cloudlesson.model;

import java.io.Serializable;

public class Notice implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.topic_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    private Long topicId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.target_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    private Long targetId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.reply_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    private Long replyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.gmt_create
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.status
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    private Byte status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notice
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.id
     *
     * @return the value of notice.id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.id
     *
     * @param id the value for notice.id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.topic_id
     *
     * @return the value of notice.topic_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.topic_id
     *
     * @param topicId the value for notice.topic_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.target_id
     *
     * @return the value of notice.target_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.target_id
     *
     * @param targetId the value for notice.target_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.reply_id
     *
     * @return the value of notice.reply_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public Long getReplyId() {
        return replyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.reply_id
     *
     * @param replyId the value for notice.reply_id
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.gmt_create
     *
     * @return the value of notice.gmt_create
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.gmt_create
     *
     * @param gmtCreate the value for notice.gmt_create
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.status
     *
     * @return the value of notice.status
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.status
     *
     * @param status the value for notice.status
     *
     * @mbg.generated Sat May 09 21:06:47 CST 2020
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}