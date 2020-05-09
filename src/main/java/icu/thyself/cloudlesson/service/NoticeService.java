package icu.thyself.cloudlesson.service;

/**
 * @author luoyelun
 * @date 2020/5/9 16:44
 */

public interface NoticeService {
    void createNotice(Long topicId, Long targetId, Long replyId);

    int getNoticeCount(Long accountId);
}
