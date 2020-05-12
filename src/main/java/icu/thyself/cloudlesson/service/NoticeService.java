package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.NoticeDTO;
import icu.thyself.cloudlesson.model.Notice;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/9 16:44
 */

public interface NoticeService {
    /**
     * 创建通知
     */
    void createNotice(Long topicId, Long targetId, Long replyId);

    /**
     * 获得未读通知数
     */
    int getNoticeByAccountIdCount(Long accountId);

    /**
     * 获得通知
     */
    List<NoticeDTO> getNotices(Long aid, int pageNum);

    int updateNoticeStatus(Long noticeId);
}
