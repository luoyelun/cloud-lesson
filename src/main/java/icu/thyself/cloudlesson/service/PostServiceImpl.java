package icu.thyself.cloudlesson.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import icu.thyself.cloudlesson.dto.PostDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.PostMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.Post;
import icu.thyself.cloudlesson.model.PostExample;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/5 21:42
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    PostMapper postMapper;

    /**
     * 最新主题排序列表
     */
    @Override
    public List<PostDTO> latestPostList(int pageNum) {
        PageHelper.startPage(pageNum, 20);
        PostExample postExample = new PostExample();
        postExample.setOrderByClause("gmt_create desc");
        //获得相应条数的主题，与分页信息
        PageInfo<Post> posts = new PageInfo<>(postMapper.selectByExample(postExample));
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : posts.getList()) {
            PostDTO postDTO = new PostDTO();
            Account account = accountMapper.selectByPrimaryKey(post.getAuthor());
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setAuthorId(post.getAuthor());
            postDTO.setAuthorAvatar(account.getHeader());
            postDTO.setAuthorName(account.getName());
            postDTO.setTags(post.getTags());
            postDTO.setReplyCount(post.getReplyCount());
            postDTO.setViewCount(post.getViewCount());
            postDTO.setPageMaxNum(posts.getPages());
            postDTO.setPageMaxNum(posts.getPageNum());
            //如果创建时间与回复时间相等 则0回复
            if (post.getGmtCreate().equals(post.getGmtRecent())) {
                postDTO.setInfo(postDTO.getAuthorName() + "发布于" + DateFormatUtils.format(post.getGmtCreate(), "yyyy/MM/dd HH:mm"));
            }
            //回复时间大于创建时间 不是0回复
            else if (post.getGmtRecent() > post.getGmtCreate()) {
                postDTO.setInfo(accountMapper.selectByPrimaryKey(post.getRecentReplyAccountId()).getName()
                        + "回复于"
                        + DateFormatUtils.format(post.getGmtRecent(), "yyyy/MM/dd HH:mm"));
            }
            postDTOList.add(postDTO);
        }
        return postDTOList;
    }
}
