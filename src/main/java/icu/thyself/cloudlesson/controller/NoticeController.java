package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.mapper.NoticeMapper;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luoyelun
 * @date 2020/5/12 22:14
 */
@Controller
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping("/t/{tid}/n/{nid}")
    public String noticeDelete(@PathVariable("tid") Long tid, @PathVariable("nid") Long nid, HttpServletRequest request) {
        noticeService.updateNoticeStatus(nid);
        int account = noticeService.getNoticeByAccountIdCount(((Account) request.getSession().getAttribute("account")).getId());
        request.getSession().setAttribute("noticeCount", account);
        return "redirect:/t/" + tid;
    }
}
