package icu.thyself.cloudlesson.controller;

import com.alibaba.fastjson.JSON;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.service.AccountService;
import icu.thyself.cloudlesson.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @author luoyelun
 * @date 2020/5/9 22:14
 */
@Controller
public class AttentionController {
    @Autowired
    private AttentionService attentionService;

    @Autowired
    private AccountService accountService;

    @ResponseBody
    @PostMapping("/addAttention")
    public ResultDTO addAttention(@RequestBody String tid, Principal principal) {
        Long topicId = JSON.parseObject(tid).getLong("tid");
        Account account = accountService.selectAccountByUsername(principal.getName());
        if (attentionService.addAttention(account.getId(), topicId)) {
            return new ResultDTO(200, "关注成功");
        } else {
            return new ResultDTO(201, "关注失败");
        }
    }

    @ResponseBody
    @PostMapping("/removeAttention")
    public ResultDTO removeAttention(@RequestBody String tid, Principal principal) {
        Long topicId = JSON.parseObject(tid).getLong("tid");
        Account account = accountService.selectAccountByUsername(principal.getName());
        if (attentionService.removeAttention(account.getId(), topicId)) {
            return new ResultDTO(202, "已取消");
        } else {
            return new ResultDTO(203, "取消失败");
        }
    }
}
