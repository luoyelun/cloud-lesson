package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author luoyelun
 * @date 2020/5/7 20:14
 */
@Controller
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping("/t/{tid}")
    public String topic(@PathVariable(name = "tid") Long tid) {

        return "";
    }
}
