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


    @Override
    public List<PostDTO> getPostList(int pageNum, String tag, String keyWord, String orderBy) {
        PageHelper.startPage(pageNum, 10);
        PostExample postExample = new PostExample();
        //是否通过标签查询
        if (tag != null) {
            postExample.createCriteria().andTagsLike("%" + tag + "%");
        }
        //是否进行模糊查询
        if (keyWord != null) {
            postExample.createCriteria().andTitleLike("%" + keyWord + "%");
        }
        if ("create".equals(orderBy)) {
            //最新发布
            postExample.setOrderByClause("gmt_create desc");
        } else if ("most_reply".equals(orderBy)) {
            //最多回复
            postExample.setOrderByClause("reply_count desc");
        } else if ("recent".equals(orderBy)) {
            //最近回复
            postExample.setOrderByClause("gmt_recent desc");
        } else if ("most_view".equals(orderBy)) {
            //最多点击
            postExample.setOrderByClause("view_count desc");
        }
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
            postDTO.setPresentPageNum(posts.getPageNum());
            //如果创建时间与回复时间相等
            if (post.getGmtCreate().equals(post.getGmtRecent())) {
                postDTO.setInfo(postDTO.getAuthorName() + "发布于" + DateFormatUtils.format(post.getGmtCreate(), "yyyy/MM/dd HH:mm"));
            }
            //回复时间大于创建时间
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
