package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.dto.CommentDTO;
import icu.thyself.cloudlesson.dto.TopicDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.TopicExtMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/7 20:14
 */
@Controller
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;
    @Autowired
    TopicExtMapper topicExtMapper;
    @Autowired
    NoticeService noticeService;
    @Autowired
    AccountService accountService;
    @Autowired
    AttentionService attentionService;

    @GetMapping("/t/{tid}")
    public String topic(@PathVariable(name = "tid") Long tid, Model model, Principal principal) {
        TopicDTO topicDTO = topicService.getById(tid);
        if (topicDTO == null) {
            return "redirect:/";
        }
        //主题信息
        model.addAttribute("topic", topicDTO);
        topicExtMapper.updateViewCount(tid);
        //回复列表
        List<CommentDTO> comments = commentService.getCommentsByTopicId(tid);
        model.addAttribute("comments", comments);
        //传递是否关注该主题
        if (principal != null) {
            Account account = accountService.selectAccountByUsername(principal.getName());
            model.addAttribute("isAttention", attentionService.isAttention(account.getId(), tid));
        }
        return "topic";
    }
}
