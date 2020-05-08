package icu.thyself.cloudlesson.model;

import java.io.Serializable;

public class Account implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.id
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.username
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.password
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.name
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.header
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private String header;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.gmt_create
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.qq
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private String qq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.wechat
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private String wechat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account.role
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private String role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.id
     *
     * @return the value of account.id
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.id
     *
     * @param id the value for account.id
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.username
     *
     * @return the value of account.username
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.username
     *
     * @param username the value for account.username
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.password
     *
     * @return the value of account.password
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.password
     *
     * @param password the value for account.password
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.name
     *
     * @return the value of account.name
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.name
     *
     * @param name the value for account.name
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.header
     *
     * @return the value of account.header
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public String getHeader() {
        return header;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.header
     *
     * @param header the value for account.header
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.gmt_create
     *
     * @return the value of account.gmt_create
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.gmt_create
     *
     * @param gmtCreate the value for account.gmt_create
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.qq
     *
     * @return the value of account.qq
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public String getQq() {
        return qq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.qq
     *
     * @param qq the value for account.qq
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.wechat
     *
     * @return the value of account.wechat
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.wechat
     *
     * @param wechat the value for account.wechat
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account.role
     *
     * @return the value of account.role
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account.role
     *
     * @param role the value for account.role
     *
     * @mbg.generated Thu May 07 21:15:11 CST 2020
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}