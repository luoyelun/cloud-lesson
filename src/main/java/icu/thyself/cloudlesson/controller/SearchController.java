package icu.thyself.cloudlesson.controller;

import com.alibaba.fastjson.JSON;
import icu.thyself.cloudlesson.dto.IndexTopicDTO;
import icu.thyself.cloudlesson.service.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/10 23:03
 */
@Controller
public class SearchController {
    @Autowired
    TopicService topicService;

    @GetMapping("/search")
    public String toSearch(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "keyword", required = false) String keyword, Model model) {
        if (StringUtils.isEmpty(keyword)) {
            return "redirect:/";
        }
        List<IndexTopicDTO> topicList = topicService.getTopicList(pageNum, null, keyword, "create");
        model.addAttribute("topicList", topicList);
        model.addAttribute("keyword", keyword);
        return "search";
    }

    @ResponseBody
    @GetMapping("/search/bypagenum")
    public String search(@RequestParam(value = "pageNum") Integer pageNum,
                         @RequestParam(name = "keyword") String keyword) {
        return JSON.toJSONString(topicService.getTopicList(pageNum, null, keyword, "create"));
    }
}
