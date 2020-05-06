package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.config.UserInfo;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.mapper.AccountMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AccountExample;
import icu.thyself.cloudlesson.model.Post;
import icu.thyself.cloudlesson.service.PublishService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/4 16:20
 */
@Controller
public class PublishController {
    @Autowired
    PublishService publishService;

    @GetMapping("/publish")
    public String toPublish() {
        return "publish";
    }

    @ResponseBody
    @PostMapping("/publish")
    public ResultDTO publish(@RequestBody Post post, Principal principal) {
        if (StringUtils.isEmpty(post.getContent()) ||
                StringUtils.isEmpty(post.getTitle()) ||
                StringUtils.isEmpty(post.getTags())) {
            return new ResultDTO(201, "请检查主题内容是否完整");
        }
        return publishService.insertPost(post, principal.getName());
    }

}
