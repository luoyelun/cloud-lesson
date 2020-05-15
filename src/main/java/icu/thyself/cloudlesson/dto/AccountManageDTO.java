package icu.thyself.cloudlesson.dto;

import lombok.Data;

/**
 * @author luoyelun
 * @date 2020/5/15 14:34
 */
@Data
public class AccountManageDTO {
    private Long id;
    private String username;
    private String name;
    private String createTime;
    private String qq;
    private String wechat;
    private String role;
    private String status;
    private Integer pageNum;
    private Integer maxPageNum;
}
