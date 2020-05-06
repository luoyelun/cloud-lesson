package icu.thyself.cloudlesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luoyelun
 * @date 2020/5/4 11:49
 */
@Controller
public class AdminController {
    @GetMapping("/admin/management")
    public String admin() {
        return "admin";
    }
}
