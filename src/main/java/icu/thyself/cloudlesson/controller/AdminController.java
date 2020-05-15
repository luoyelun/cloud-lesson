package icu.thyself.cloudlesson.controller;

import com.alibaba.fastjson.JSON;
import com.qiniu.util.Json;
import icu.thyself.cloudlesson.dto.AccountManageDTO;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.model.Recommend;
import icu.thyself.cloudlesson.service.AccountService;
import icu.thyself.cloudlesson.service.RecommendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luoyelun
 * @date 2020/5/4 11:49
 */
@Controller
public class AdminController {
    @Autowired
    RecommendService recommendService;
    @Autowired
    AccountService accountService;

    @GetMapping("/admin/recommendManage")
    public String recommendManage(Model model) {
        List<Recommend> recommends = recommendService.getAllRecommend();
        model.addAttribute("recommends", recommends);
        return "recommend_manage";
    }

    @GetMapping("/admin/userManage")
    public String userManage(Model model) {
        List<AccountManageDTO> accounts = accountService.selectAccount(1, 10, null);
        model.addAttribute("accounts", accounts);
        return "user_manage";
    }

    @ResponseBody
    @GetMapping("/admin/userManage/{pageNum}")
    public String getUserListByPage(@PathVariable("pageNum") Integer pageNum) {
        List<AccountManageDTO> accounts = accountService.selectAccount(pageNum, 10, null);
        return JSON.toJSONString(accounts);
    }

    @ResponseBody
    @GetMapping("/admin/userManage/search/{keyword}")
    public String searchAccount(@PathVariable("keyword") String keyword) {
        List<AccountManageDTO> accounts = accountService.selectAccount(1, 0, keyword);
        return JSON.toJSONString(accounts);
    }

    @ResponseBody
    @PostMapping("/admin/user/modify")
    public ResultDTO modifyUser(@Param("status") Byte status,
                                @Param("role") String role,
                                @Param("accountId") Long accountId) {
        if ("0".equals(role)) {
            role = "ROLE_USER";
        } else {
            role = "ROLE_USER,ROLE_AUTHOR";
        }
        int i = accountService.updateStatusAndRoleByAccountId(status, role, accountId);
        if (i > 0) {
            return new ResultDTO(200, "修改成功");
        }
        return new ResultDTO(201, "修改失败，刷新后重试");
    }
}
