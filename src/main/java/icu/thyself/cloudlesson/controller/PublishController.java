package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Topic;
import icu.thyself.cloudlesson.service.PublishService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

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
    public ResultDTO publish(@RequestBody Topic topic, Principal principal) {
        if (StringUtils.isEmpty(topic.getContent()) ||
                StringUtils.isEmpty(topic.getTitle()) ||
                StringUtils.isEmpty(topic.getTags())) {
            return new ResultDTO(201, "请检查主题内容是否完整");
        }
        return publishService.insertPost(topic, principal.getName());
    }

}
