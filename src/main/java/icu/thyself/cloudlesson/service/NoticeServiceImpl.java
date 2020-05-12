package icu.thyself.cloudlesson.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.thyself.cloudlesson.dto.NoticeDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.NoticeMapper;
import icu.thyself.cloudlesson.mapper.TopicMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.Notice;
import icu.thyself.cloudlesson.model.NoticeExample;
import icu.thyself.cloudlesson.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    TopicMapper topicMapper;

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
     *
     * @return
     */
    @Override
    public int getNoticeByAccountIdCount(Long accountId) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andStatusEqualTo((byte) 0)
                .andTargetIdEqualTo(accountId);
        return noticeMapper.selectByExample(noticeExample).size();
    }

    @Override
    public List<NoticeDTO> getNotices(Long aid, int pageNum) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.setOrderByClause("gmt_create desc");
        noticeExample.createCriteria().andTargetIdEqualTo(aid);
//        PageHelper.startPage(pageNum, 15);
        //查找到我回复我的所有通知
//        PageInfo<Notice> notices = new PageInfo<>(noticeMapper.selectByExample(noticeExample));
        List<Notice> notices = noticeMapper.selectByExample(noticeExample);
        List<NoticeDTO> noticedtos = new ArrayList<>();
        for (Notice n : notices) {
            NoticeDTO noticeDTO = new NoticeDTO();
            //找到回复人信息
            Account account = accountService.selectAccountById(n.getReplyId());
            //找到回复帖子信息
            Topic topic = topicMapper.selectByPrimaryKey(n.getTopicId());

            noticeDTO.setNoticeId(n.getId());
            noticeDTO.setTopicId(n.getTopicId());
            noticeDTO.setTitle(topic.getTitle());
            noticeDTO.setReplyAccountId(account.getId());
            noticeDTO.setReplyAccountName(account.getName());
            if (n.getStatus() == 0) {
                noticeDTO.setStatus(true);
            } else {
                noticeDTO.setStatus(false);
            }
            noticedtos.add(noticeDTO);
        }
        return noticedtos;
    }

    @Override
    public int updateNoticeStatus(Long noticeId) {
        Notice notice = new Notice();
        notice.setId(noticeId);
        notice.setStatus((byte) 1);
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }
}
