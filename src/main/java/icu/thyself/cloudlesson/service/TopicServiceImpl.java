package icu.thyself.cloudlesson.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.thyself.cloudlesson.dto.IndexTopicDTO;
import icu.thyself.cloudlesson.dto.TopicDTO;
import icu.thyself.cloudlesson.mapper.*;
import icu.thyself.cloudlesson.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/5 21:42
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    AttentionMapper attentionMapper;
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public List<IndexTopicDTO> getTopicList(int pageNum, int pageSize, String tag, String keyWord, String orderBy) {
        PageHelper.startPage(pageNum, pageSize);
        TopicExample topicExample = new TopicExample();
        //是否通过标签查询
        if (tag != null) {
            topicExample.createCriteria().andTagsLike("%" + tag + "%");
        }
        //是否进行模糊查询
        if (keyWord != null) {
            topicExample.createCriteria().andTitleLike("%" + keyWord + "%");
        }
        if ("create".equals(orderBy)) {
            //最新发布
            topicExample.setOrderByClause("gmt_create desc");
        } else if ("most_reply".equals(orderBy)) {
            //最多回复
            topicExample.setOrderByClause("reply_count desc");
        } else if ("recent".equals(orderBy)) {
            //最近回复
            topicExample.setOrderByClause("gmt_recent desc");
        } else if ("most_view".equals(orderBy)) {
            //最多点击
            topicExample.setOrderByClause("view_count desc");
        }
        //获得相应条数的主题，与分页信息
        PageInfo<Topic> topics = new PageInfo<>(topicMapper.selectByExample(topicExample));
        List<IndexTopicDTO> indexTopicDTOList = new ArrayList<>();
        for (Topic topic : topics.getList()) {
            IndexTopicDTO indexTopicDTO = new IndexTopicDTO();
            Account account = accountMapper.selectByPrimaryKey(topic.getAuthor());
            indexTopicDTO.setId(topic.getId());
            indexTopicDTO.setTitle(topic.getTitle());
            indexTopicDTO.setAuthorId(topic.getAuthor());
            indexTopicDTO.setAuthorAvatar(account.getHeader());
            indexTopicDTO.setAuthorName(account.getName());
            indexTopicDTO.setTags(topic.getTags());
            indexTopicDTO.setReplyCount(topic.getReplyCount());
            indexTopicDTO.setViewCount(topic.getViewCount());
            indexTopicDTO.setPageMaxNum(topics.getPages());
            indexTopicDTO.setPresentPageNum(topics.getPageNum());
            //如果创建时间与回复时间相等
            if (topic.getGmtCreate().equals(topic.getGmtRecent())) {
                indexTopicDTO.setInfo(indexTopicDTO.getAuthorName() + "发布于" + DateFormatUtils.format(topic.getGmtCreate(), "yyyy/MM/dd HH:mm"));
            }
            //回复时间大于创建时间
            else if (topic.getGmtRecent() > topic.getGmtCreate()) {
                indexTopicDTO.setInfo(accountMapper.selectByPrimaryKey(topic.getRecentReplyAccountId()).getName()
                        + "回复于"
                        + DateFormatUtils.format(topic.getGmtRecent(), "yyyy/MM/dd HH:mm"));
            }
            indexTopicDTOList.add(indexTopicDTO);
        }
        return indexTopicDTOList;
    }

    @Override
    public TopicDTO getById(Long tid) {
        Topic topic = topicMapper.selectByPrimaryKey(tid);
        if (topic == null) {
            return null;
        }
        TopicDTO topicDTO = new TopicDTO();
        topicDTO.setId(topic.getId());
        topicDTO.setTitle(topic.getTitle());
        topicDTO.setContent(topic.getContent());
        topicDTO.setTags(topic.getTags());
        if (!StringUtils.isEmpty(topic.getVideoLink())) {
            topicDTO.setVideoLink(topic.getVideoLink());
        }
        Account account = accountMapper.selectByPrimaryKey(topic.getAuthor());
        topicDTO.setAuthorId(account.getId());
        topicDTO.setAuthorAvatar(account.getHeader());
        topicDTO.setAuthorName(account.getName());
        topicDTO.setCreateDate(DateFormatUtils.format(topic.getGmtCreate(), "yyyy/MM/dd HH:mm"));
        if (!topic.getGmtCreate().equals(topic.getGmtModify())) {
            topicDTO.setModifyDate(DateFormatUtils.format(topic.getGmtModify(), "yyyy/MM/dd HH:mm"));
        }

        return topicDTO;
    }

    @Override
    public boolean modifyTopic(Topic topic) {
        topic.setGmtModify(System.currentTimeMillis());
        return topicMapper.updateByPrimaryKeySelective(topic) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteTopic(Long tid) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andTopicIdEqualTo(tid);
        AttentionExample attentionExample = new AttentionExample();
        attentionExample.createCriteria().andTopicIdEqualTo(tid);
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andTopicIdEqualTo(tid);
        try {
            topicMapper.deleteByPrimaryKey(tid);
            commentMapper.deleteByExample(commentExample);
            attentionMapper.deleteByExample(attentionExample);
            noticeMapper.deleteByExample(noticeExample);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public List<IndexTopicDTO> getMyTopicList(Long accountId) {
        TopicExample topicExample = new TopicExample();
        topicExample.setOrderByClause("gmt_create desc");
        topicExample.createCriteria()
                .andAuthorEqualTo(accountId);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        List<IndexTopicDTO> topicDTOList = new ArrayList<>();
        for (Topic topic : topics) {
            IndexTopicDTO indexTopicDTO = new IndexTopicDTO();
            indexTopicDTO.setId(topic.getId());
            indexTopicDTO.setTitle(topic.getTitle());
            topicDTOList.add(indexTopicDTO);
        }
        return topicDTOList;
    }
}
