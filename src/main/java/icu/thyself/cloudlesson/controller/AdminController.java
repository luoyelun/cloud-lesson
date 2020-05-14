package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.model.Recommend;
import icu.thyself.cloudlesson.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/4 11:49
 */
@Controller
public class AdminController {
    @Autowired
    RecommendService recommendService;

    @GetMapping("/admin/management")
    public String admin(Model model) {
        List<Recommend> recommends = recommendService.getAllRecommend();
        model.addAttribute("recommends", recommends);
        return "recommend_manage";
    }
}
