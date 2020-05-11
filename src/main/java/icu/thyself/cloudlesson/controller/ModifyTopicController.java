package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.dto.TopicDTO;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.service.AccountService;
import icu.thyself.cloudlesson.service.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author luoyelun
 * @date 2020/5/11 10:39
 */
@Controller
public class ModifyTopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    AccountService accountService;

    @GetMapping("/publish/modify/{tid}")
    public String modifyTopic(@PathVariable("tid") Long tid, Model model, HttpServletRequest request, Principal principal) {
        Account accountSession = (Account) request.getSession().getAttribute("account");
        TopicDTO topicDTO = topicService.getById(tid);
        //admin用户和主题创建者可以修改主题
        if (StringUtils.contains(accountSession.getRole(), "ADMIN") || accountSession.getId().equals(topicDTO.getAuthorId())) {
            model.addAttribute("topic", topicDTO);
            return "modify_topic";
        }
        return "redirect:/";
    }
}
