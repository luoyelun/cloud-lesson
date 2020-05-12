package icu.thyself.cloudlesson.dto;

import lombok.Data;

/**
 * @author luoyelun
 * @date 2020/5/8 16:36
 */
@Data
public class CommentDTO {
    //主题id
    private Long topicId;
    //主题标题
    private String topicTitle;
    //回复用户ID
    private Long accountId;
    //回复用户昵称
    private String accountName;
    //回复用户头像
    private String accountAvatar;
    //回复内容
    private String content;
    //回复目标ID
    private Long atAccountId;
    //回复目标name
    private String atAccountName;
    //回复时间
    private String replyDate;
    //当前
    private Integer presentPageNum;
    //最大页数
    private Integer pageMaxNum;
}
