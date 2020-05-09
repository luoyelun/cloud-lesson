package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.dto.CommentDTO;
import icu.thyself.cloudlesson.dto.TopicDTO;
import icu.thyself.cloudlesson.mapper.TopicExtMapper;
import icu.thyself.cloudlesson.service.CommentService;
import icu.thyself.cloudlesson.service.NoticeService;
import icu.thyself.cloudlesson.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/t/{tid}")
    public String topic(@PathVariable(name = "tid") Long tid, Model model) {
        TopicDTO topicDTO = topicService.getById(tid);
        if (topicDTO == null) {
            return "redirect:/";
        }
        model.addAttribute("topic", topicDTO);
        topicExtMapper.updateViewCount(tid);
        List<CommentDTO> comments = commentService.getCommentsByTopicId(tid);
        model.addAttribute("comments", comments);
        return "topic";
    }
}
