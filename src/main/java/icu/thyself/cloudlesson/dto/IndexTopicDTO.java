package icu.thyself.cloudlesson.dto;

import icu.thyself.cloudlesson.model.Account;
import lombok.Data;

/**
 * @author luoyelun
 * @date 2020/5/5 21:21
 */
@Data
public class IndexTopicDTO {
    //主题ID
    private Long id;
    //主题标题
    private String title;
    //作者ID
    private Long authorId;
    //作者头像
    private String authorAvatar;
    //作者昵称
    private String authorName;
    //主题标签
    private String tags;
    //次要信息
    private String info;
    //回复数
    private Integer replyCount;
    //点击数
    private Integer viewCount;
    //当前
    private Integer presentPageNum;
    //最大页数
    private Integer pageMaxNum;
}
