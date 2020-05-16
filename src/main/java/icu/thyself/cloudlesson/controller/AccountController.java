package icu.thyself.cloudlesson.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.qiniu.storage.model.DefaultPutRet;
import icu.thyself.cloudlesson.dto.*;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.AuthorApply;
import icu.thyself.cloudlesson.model.Notice;
import icu.thyself.cloudlesson.provider.QiNiuProvider;
import icu.thyself.cloudlesson.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/11 21:01
 */
@Controller
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    AttentionService attentionService;
    @Autowired
    QiNiuProvider qiNiuProvider;
    @Autowired
    AuthorApplyService authorApplyService;

    @GetMapping("/u/{aid}")
    public String personInfo(@PathVariable("aid") Long aid, Model model) {
        Account account = accountService.selectAccountById(aid);
        if (account == null) {
            return "redirect:/";
        }
        model.addAttribute("account", account);
        List<IndexTopicDTO> topicDTOList = topicService.getMyTopicList(account.getId());
        model.addAttribute("topics", topicDTOList);
        List<CommentDTO> commentDTOList = commentService.getCommentsByAccountId(aid, 1);
        model.addAttribute("comments", commentDTOList);
        List<NoticeDTO> notices = noticeService.getNotices(aid, 1);
        model.addAttribute("notices", notices);
        List<AttentionDTO> attentions = attentionService.getAttentions(aid);
        model.addAttribute("attentions", attentions);
        return "personal_page";
    }

    @ResponseBody
    @PostMapping("/u/avatarupload")
    public ResultDTO avatarUpload(@RequestParam("fileName") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile.isEmpty()) {
            return new ResultDTO(201, "选择文件");
        }
        try {
            DefaultPutRet upload = qiNiuProvider.upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), multipartFile.getContentType());
            Account account = (Account) request.getSession().getAttribute("account");
            accountService.updateAccountAvatar(account.getId(), "http://q9p1v1fsb.bkt.clouddn.com/" + upload.key);
            request.getSession().setAttribute("account", accountService.selectAccountById(account.getId()));
            return new ResultDTO(200, "上传完成");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultDTO(201, "上传失败");
        }
    }

    @ResponseBody
    @PostMapping("/u/modifyAccountInfo")
    public ResultDTO modifyAccountInfo(@RequestBody Account account, HttpServletRequest request) {
        if (!StringUtils.isEmpty(account.getPassword())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        account.setId(((Account) request.getSession().getAttribute("account")).getId());
        int i = accountService.updateAccountInfo(account);
        if (i > 0) {
            request.getSession().setAttribute("account", accountService.selectAccountById(account.getId()));
            return new ResultDTO(200, "修改成功");
        } else {
            return new ResultDTO(200, "修改失败");
        }
    }

    @ResponseBody
    @PostMapping("/u/authorApply")
    public ResultDTO authorApply(@Param("reason") String reason, Principal principal) {
        if (principal == null) {
            return new ResultDTO(201, "提交申请失败，请重试。");
        }
        if (StringUtils.isEmpty(reason)) {
            return new ResultDTO(201, "请填写申请理由");
        }
        Long accountId = accountService.selectAccountByUsername(principal.getName()).getId();
        if (authorApplyService.isExistApply(accountId)) {
            return new ResultDTO(201, "已经申请过了");
        }
        AuthorApply authorApply = new AuthorApply();
        authorApply.setAccountId(accountId);
        authorApply.setApplyReason(reason);
        authorApply.setGmtCreate(System.currentTimeMillis());
        int i = authorApplyService.createAuthorApply(authorApply);
        if (i > 0) {
            return new ResultDTO(200, "提交成功");
        }
        return new ResultDTO(201, "申请失败，请重试。");
    }
}
