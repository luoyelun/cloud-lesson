package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.dto.PostDTO;
import icu.thyself.cloudlesson.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/2 18:43
 */
@Controller
public class IndexController {
    @Autowired
    PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        List<PostDTO> postDTOList = postService.latestPostList(1);
        model.addAttribute("postList", postDTOList);
        return "index";
    }

    @GetMapping("/index/latest/{pageNum}")
    public List<PostDTO> getLatestPostListByPageNum(@PathVariable(name = "pageNum") int pageNum) {

        return null;
    }
}
