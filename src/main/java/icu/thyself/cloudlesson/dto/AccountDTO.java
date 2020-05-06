package icu.thyself.cloudlesson.dto;

import lombok.Data;

/**
 * @author luoyelun
 * @date 2020/5/4 14:01
 */
@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String header;
    private Long gmtCreate;
    private String qq;
    private String wechat;
    private String role;
    private String captcha;
}
