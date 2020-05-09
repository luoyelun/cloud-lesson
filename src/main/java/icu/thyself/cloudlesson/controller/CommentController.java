package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.Comment;
import icu.thyself.cloudlesson.service.AccountService;
import icu.thyself.cloudlesson.service.CommentService;
import icu.thyself.cloudlesson.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author luoyelun
 * @date 2020/5/8 16:51
 */
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    AccountService accountService;
    @Autowired
    NoticeService noticeService;

    @ResponseBody
    @PostMapping("/comment")
    public ResultDTO comment(@RequestBody Comment comment, Principal principal) {
        if (comment == null) {
            return new ResultDTO(201, "回复失败，刷新页面重试");
        }
        String username = principal.getName();
        Account account = accountService.selectAccountByUsername(username);
        comment.setAccountId(account.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        return commentService.createComment(comment);
    }
}
