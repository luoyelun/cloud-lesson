package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.security.Principal;

/**
 * @author luoyelun
 * @date 2020/5/2 18:19
 */
@Controller
public class LoginController {
    @Autowired
    AccountService accountService;

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/login/form")
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe) {
    }

    @GetMapping("/loginerror")
    public String logerror(Model model) {
        model.addAttribute("msg", "登陆失败，请确认账号密码");
        return "login";
    }


}
