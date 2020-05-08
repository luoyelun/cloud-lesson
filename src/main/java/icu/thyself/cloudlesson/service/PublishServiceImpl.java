package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.TopicMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AccountExample;
import icu.thyself.cloudlesson.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/5 12:07
 */
@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    TopicMapper topicMapper;

    @Override
    public ResultDTO insertPost(Topic topic, String username) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        Long id = accounts.get(0).getId();
        topic.setAuthor(id);
        topic.setGmtCreate(System.currentTimeMillis());
        topic.setGmtModify(topic.getGmtCreate());
        topic.setGmtRecent(topic.getGmtCreate());
        topic.setRecentReplyAccountId(id);
        try {
            topicMapper.insertSelective(topic);
            return new ResultDTO(200, "发布成功");
        } catch (Exception e) {
            return new ResultDTO(201, "发布失败");
        }
    }
}
