package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.NoticeMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.Notice;
import icu.thyself.cloudlesson.model.NoticeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/9 16:44
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;

    /**
     * 创建一个通知
     * 主题id，被通知人id，回复人id
     */
    @Override
    public void createNotice(Long topicId, Long targetId, Long replyId) {
        Notice notice = new Notice();
        notice.setTopicId(topicId);
        notice.setTargetId(targetId);
        notice.setReplyId(replyId);
        notice.setGmtCreate(System.currentTimeMillis());
        notice.setStatus((byte) 0);
        noticeMapper.insert(notice);
    }

    /**
     * 根据用户名查找到未读通知数
     */
    @Override
    public int getNoticeCount(Long accountId) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andStatusEqualTo((byte) 0)
                .andTargetIdEqualTo(accountId);
        List<Notice> notices = noticeMapper.selectByExample(noticeExample);
        return notices.size();
    }
}
