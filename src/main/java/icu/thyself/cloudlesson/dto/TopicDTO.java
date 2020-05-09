package icu.thyself.cloudlesson.dto;

import lombok.Data;

/**
 * @author luoyelun
 * @date 2020/5/7 21:05
 */
@Data
public class TopicDTO {
    //主题ID
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //视频链接
    private String videoLink;
    //标签
    private String tags;
    //作者ID
    private Long authorId;
    //作者头像
    private String authorAvatar;
    //作者昵称
    private String authorName;
    //创建时间
    private String createDate;
    //修改时间
    private String modifyDate;

}
