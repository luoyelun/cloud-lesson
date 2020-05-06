package icu.thyself.cloudlesson.model;

import java.io.Serializable;

public class Classification implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column classification.id
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column classification.classification_name
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    private String classificationName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table classification
     *
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column classification.id
     *
     * @return the value of classification.id
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column classification.id
     *
     * @param id the value for classification.id
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column classification.classification_name
     *
     * @return the value of classification.classification_name
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    public String getClassificationName() {
        return classificationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column classification.classification_name
     *
     * @param classificationName the value for classification.classification_name
     * @mbg.generated Tue May 05 22:05:32 CST 2020
     */
    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName == null ? null : classificationName.trim();
    }
}