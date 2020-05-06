package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.PostMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AccountExample;
import icu.thyself.cloudlesson.model.Post;
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
    PostMapper postMapper;

    @Override
    public ResultDTO insertPost(Post post, String username) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andUsernameEqualTo(username);
        List<Account> accounts = accountMapper.selectByExample(accountExample);
        Long id = accounts.get(0).getId();
        post.setAuthor(id);
        post.setGmtCreate(System.currentTimeMillis());
        post.setGmtModify(post.getGmtCreate());
        post.setGmtRecent(post.getGmtCreate());
        post.setRecentReplyAccountId(id);
        try {
            postMapper.insertSelective(post);
            return new ResultDTO(200, "发布成功");
        } catch (Exception e) {
            return new ResultDTO(201, "发布失败");
        }
    }
}
