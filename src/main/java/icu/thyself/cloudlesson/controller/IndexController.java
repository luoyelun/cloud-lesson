package icu.thyself.cloudlesson.controller;

import com.alibaba.fastjson.JSON;
import icu.thyself.cloudlesson.dto.PostDTO;
import icu.thyself.cloudlesson.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/2 18:43
 */
@Controller
public class IndexController {
    @Autowired
    PostService postService;

    //首页
    @GetMapping("/")
    public String index(Model model) {
        List<PostDTO> postDTOList = postService.getPostList(1, null, null, "create");
        model.addAttribute("postList", postDTOList);
        return "index";
    }

    /**
     * 最新主题
     */
    @ResponseBody
    @GetMapping("/index/latest/{pageNum}")
    public String getLatestPostListByPageNum(@PathVariable(name = "pageNum") int pageNum) {
        return JSON.toJSONString(postService.latestPostList(pageNum));
    }

    /**
     * 最多回复
     */
    @ResponseBody
    @GetMapping("/index/mostreply/{pageNum}")
    public String getMostReplyPostListByPageNum(@PathVariable(name = "pageNum") int pageNum) {
        return JSON.toJSONString(postService.getPostList(pageNum, null, null, "most_reply"));
    }

    /**
     * 最多点击
     */
    @ResponseBody
    @GetMapping("/index/mostview/{pageNum}")
    public String getMostViewPostListByPageNum(@PathVariable(name = "pageNum") int pageNum) {
        return JSON.toJSONString(postService.getPostList(pageNum, null, null, "most_view"));
    }

    /**
     * 最近回复
     */
    @ResponseBody
    @GetMapping("/index/recent/{pageNum}")
    public String getRecentPostListByPageNum(@PathVariable(name = "pageNum") int pageNum) {
        return JSON.toJSONString(postService.getPostList(pageNum, null, null, "recent"));
    }

    @ResponseBody
    @GetMapping("/test")
    public Object test() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
