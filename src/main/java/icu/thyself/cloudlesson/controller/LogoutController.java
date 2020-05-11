package icu.thyself.cloudlesson.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luoyelun
 * @date 2020/5/11 11:00
 */
@Controller
public class LogoutController {
    public String logout(HttpServletRequest request) {
        return "/";
    }
}
