package icu.thyself.cloudlesson.controller;

import com.alibaba.fastjson.JSON;
import icu.thyself.cloudlesson.dto.IndexTopicDTO;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.service.AccountService;
import icu.thyself.cloudlesson.service.NoticeService;
import icu.thyself.cloudlesson.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/2 18:43
 */
@Controller
public class IndexController {
    @Autowired
    TopicService topicService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    AccountService accountService;

    /**
     * 首页
     */
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request, Principal principal) {
        List<IndexTopicDTO> indexTopicDTOList = topicService.getTopicList(1, null, null, "create");
        if (principal != null) {
            String username = principal.getName();
            Account account = accountService.selectAccountByUsername(username);
            request.getSession().setAttribute("noticeCount", noticeService.getNoticeCount(account.getId()));
            request.getSession().setAttribute("account", account);
        }
        model.addAttribute("topicList", indexTopicDTOList);
        return "index";
    }


    /**
     * 最新主题
     */
    @ResponseBody
    @GetMapping("/index/latest/{pageNum}")
    public String getLatestPostList(@PathVariable(name = "pageNum") int pageNum) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, null, null, "create"));
    }

    /**
     * 最多回复
     */
    @ResponseBody
    @GetMapping("/index/mostreply/{pageNum}")
    public String getMostReplyPostList(@PathVariable(name = "pageNum") int pageNum) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, null, null, "most_reply"));
    }

    /**
     * 最多点击
     */
    @ResponseBody
    @GetMapping("/index/mostview/{pageNum}")
    public String getMostViewPostList(@PathVariable(name = "pageNum") int pageNum) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, null, null, "most_view"));
    }

    /**
     * 最近回复
     */
    @ResponseBody
    @GetMapping("/index/recent/{pageNum}")
    public String getRecentPostList(@PathVariable(name = "pageNum", required = false) int pageNum) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, null, null, "recent"));
    }

    /**
     * 最近回复，根据标签
     */
    @ResponseBody
    @GetMapping("/index/recent/{tag}/{pageNum}")
    public String getRecentPostListByTag(@PathVariable(name = "pageNum") int pageNum, @PathVariable(name = "tag") String tag) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, tag, null, "recent"));
    }

    /**
     * 最多点击，根据标签
     */
    @ResponseBody
    @GetMapping("/index/mostview/{tag}/{pageNum}")
    public String getMostViewPostListByTag(@PathVariable(name = "pageNum") int pageNum, @PathVariable(name = "tag") String tag) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, tag, null, "most_view"));
    }

    /**
     * 最多回复，根据标签
     */
    @ResponseBody
    @GetMapping("/index/mostreply/{tag}/{pageNum}")
    public String getMostReplyPostListByTag(@PathVariable(name = "pageNum") int pageNum, @PathVariable(name = "tag") String tag) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, tag, null, "most_reply"));
    }

    /**
     * 最新主题，根据标签
     */
    @ResponseBody
    @GetMapping("/index/latest/{tag}/{pageNum}")
    public String getLatestPostListByTag(@PathVariable(name = "pageNum") int pageNum, @PathVariable(name = "tag") String tag) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, tag, null, "create"));
    }


}
