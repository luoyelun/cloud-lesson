package icu.thyself.cloudlesson.model;

import java.io.Serializable;

public class Recommend implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table recommend
     *
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recommend.id
     *
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recommend.topic_link
     *
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    private String topicLink;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recommend.title
     *
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    private String title;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recommend.description
     *
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    private String description;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recommend.img_link
     *
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    private String imgLink;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recommend.id
     *
     * @return the value of recommend.id
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recommend.id
     *
     * @param id the value for recommend.id
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recommend.topic_link
     *
     * @return the value of recommend.topic_link
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public String getTopicLink() {
        return topicLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recommend.topic_link
     *
     * @param topicLink the value for recommend.topic_link
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public void setTopicLink(String topicLink) {
        this.topicLink = topicLink == null ? null : topicLink.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recommend.title
     *
     * @return the value of recommend.title
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recommend.title
     *
     * @param title the value for recommend.title
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recommend.description
     *
     * @return the value of recommend.description
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recommend.description
     *
     * @param description the value for recommend.description
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recommend.img_link
     *
     * @return the value of recommend.img_link
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public String getImgLink() {
        return imgLink;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recommend.img_link
     *
     * @param imgLink the value for recommend.img_link
     * @mbg.generated Thu May 14 17:27:42 CST 2020
     */
    public void setImgLink(String imgLink) {
        this.imgLink = imgLink == null ? null : imgLink.trim();
    }
}