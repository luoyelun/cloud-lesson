package icu.thyself.cloudlesson.dto;

import icu.thyself.cloudlesson.model.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/8 16:36
 */
@Data
public class CommentDTO {
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
}
