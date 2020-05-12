package icu.thyself.cloudlesson.dto;

import lombok.Data;

/**
 * @author luoyelun
 * @date 2020/5/11 22:23
 */
@Data
public class NoticeDTO {
    //通知ID
    private Long noticeId;
    //主题ID
    private Long topicId;
    //主题标题
    private String title;
    //回复人ID
    private Long replyAccountId;
    //回复人昵称
    private String replyAccountName;
    //阅读状态
    private boolean status;
}
