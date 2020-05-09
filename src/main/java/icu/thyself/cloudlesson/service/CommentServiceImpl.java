package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.CommentDTO;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.CommentMapper;
import icu.thyself.cloudlesson.mapper.TopicExtMapper;
import icu.thyself.cloudlesson.mapper.TopicMapper;
import icu.thyself.cloudlesson.model.*;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/8 14:32
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    TopicExtMapper topicExtMapper;
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    NoticeService noticeService;

    /**
     * 获得tid主题下的所有评论
     */
    @Override
    public List<CommentDTO> getCommentsByTopicId(Long tid) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andTopicIdEqualTo(tid);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment c : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setAccountId(c.getAccountId());
            Account replyAccount = accountMapper.selectByPrimaryKey(c.getAccountId());
            commentDTO.setAccountName(replyAccount.getName());
            commentDTO.setAccountAvatar(replyAccount.getHeader());
            commentDTO.setReplyDate(DateFormatUtils.format(c.getGmtCreate(), "yyyy/MM/dd HH:mm"));
            commentDTO.setContent(c.getContent());
            if (c.getParentId() != null) {
                Account a = accountMapper.selectByPrimaryKey(c.getParentId());
                commentDTO.setAtAccountId(a.getId());
                commentDTO.setAtAccountName(a.getName());
            }
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultDTO createComment(Comment comment) {
        try {
            //插入回复
            commentMapper.insertSelective(comment);
            //更新回复数
            topicExtMapper.updateReplyCount(comment.getTopicId());
            //更新回复时间
            Topic topic = new Topic();
            topic.setGmtRecent(System.currentTimeMillis());
            topic.setId(comment.getTopicId());
            topicMapper.updateByPrimaryKeySelective(topic);
            //创建通知
            //通知主题创建者
            Topic t = topicMapper.selectByPrimaryKey(comment.getTopicId());
            noticeService.createNotice(comment.getTopicId(), t.getAuthor(), comment.getAccountId());
            //如果回复指向其他回复，则通知该回复的创建者
            if (comment.getParentId() != null) {
                CommentExample commentExample = new CommentExample();
                //根据子评论指向的父评论找到被通知人的ID
                commentExample.createCriteria().andIdEqualTo(comment.getParentId());
                Comment parentComment = commentMapper.selectByExample(commentExample).get(0);
                noticeService.createNotice(comment.getTopicId(), parentComment.getAccountId(), comment.getAccountId());
            }
            return new ResultDTO(200, "回复成功");
        } catch (Exception e) {
            return new ResultDTO(201, "回复失败");
        }
    }
}
