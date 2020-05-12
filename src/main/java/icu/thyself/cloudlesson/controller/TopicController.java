package icu.thyself.cloudlesson.controller;

import com.qiniu.storage.model.DefaultPutRet;
import icu.thyself.cloudlesson.dto.CommentDTO;
import icu.thyself.cloudlesson.dto.FileDTO;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.dto.TopicDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.mapper.TopicExtMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.Topic;
import icu.thyself.cloudlesson.provider.QiNiuProvider;
import icu.thyself.cloudlesson.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    QiNiuProvider qiNiuProvider;

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
        //传递是否关注该主题 与是否可以修改该主题
        if (principal != null) {
            Account account = accountService.selectAccountByUsername(principal.getName());
            model.addAttribute("isAttention", attentionService.isAttention(account.getId(), tid));
            //admin用户和主题创建者可以修改主题
            if (StringUtils.contains(account.getRole(), "ADMIN") || account.getId().equals(topicDTO.getAuthorId())) {
                model.addAttribute("canModify", true);
            }
        }
        return "topic";
    }

    @GetMapping("/publish/modify/{tid}")
    public String toModifyTopic(@PathVariable("tid") Long tid, Model model, HttpServletRequest request, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        Account account = accountService.selectAccountByUsername(principal.getName());
        TopicDTO topicDTO = topicService.getById(tid);
        //admin用户和主题创建者可以修改主题
        if (StringUtils.contains(account.getRole(), "ADMIN") || account.getId().equals(topicDTO.getAuthorId())) {
            model.addAttribute("topic", topicDTO);
            return "modify_topic";
        }
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/publish/modify")
    public ResultDTO modifyTopic(@RequestBody Topic topic) {
        if (topic == null || topic.getId() == null) {
            return new ResultDTO(201, "修改失败，请刷新页面重试");
        }
        if (topicService.modifyTopic(topic)) {
            return new ResultDTO(200, "修改成功");
        } else {
            return new ResultDTO(201, "修改失败，请刷新页面重试");
        }
    }

    @GetMapping("/publish/delete/{tid}")
    public String deleteTopic(@PathVariable("tid") Long tid, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        TopicDTO topic = topicService.getById(tid);
        Account account = accountService.selectAccountByUsername(principal.getName());
        if (StringUtils.contains(account.getRole(), "ADMIN") || topic.getAuthorId().equals(account.getId())) {
            topicService.deleteTopic(tid);
        }
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("editormd-image-file");
        FileDTO fileDTO = new FileDTO();
        try {
            DefaultPutRet upload = qiNiuProvider.upload(Objects.requireNonNull(file).getInputStream(), file.getOriginalFilename(), file.getContentType());
            fileDTO.setSuccess(1);
            fileDTO.setUrl("http://q9p1v1fsb.bkt.clouddn.com/" + upload.key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileDTO;
    }

}
