package icu.thyself.cloudlesson.controller;

import icu.thyself.cloudlesson.dto.AccountDTO;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.enums.InformationEnumImpl;
import icu.thyself.cloudlesson.model.Account;
import icu.thyself.cloudlesson.service.AccountService;
import icu.thyself.cloudlesson.service.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author luoyelun
 * @date 2020/5/2 20:37
 */
@Controller
public class RegisterController {
    @Autowired
    AccountService accountService;
    @Autowired
    private MailService mailService;

    @ResponseBody
    @GetMapping("/register/sendMail")
    public ResultDTO sendMail(@RequestParam("mail") String mail) {
        Account account = new Account();
        account.setUsername(mail);
        if (accountService.isExists(account)) {
            return new ResultDTO(201, "该邮箱已注册");
        }

        String captcha = mailService.getCaptcha();
        try {
            mailService.sendMail(captcha, mail);
            return new ResultDTO(200, "邮件已发送");
        } catch (Exception e) {
            return new ResultDTO(201, "邮件发送失败");
        }


    }

    @ResponseBody
    @PostMapping("/register")
    public ResultDTO register(@RequestBody AccountDTO accountDTO) {
        if (StringUtils.isEmpty(accountDTO.getCaptcha())) {
            return new ResultDTO(201, "验证码不能为空");
        }
        boolean check = mailService.check(accountDTO.getCaptcha());
        if (!check) {
            return new ResultDTO(201, "验证码错误");
        }
        //注册信息是否为空
        if (StringUtils.isEmpty(accountDTO.getUsername()) || StringUtils.isEmpty(accountDTO.getPassword()) || StringUtils.isEmpty(accountDTO.getName())) {
            return new ResultDTO(InformationEnumImpl.REGISTER_INFO_EMPTY_OR_SPACE.getCode(),
                    InformationEnumImpl.REGISTER_INFO_EMPTY_OR_SPACE.getMessage());
        }
        String space = " ";
        if (accountDTO.getUsername().contains(space) ||
                accountDTO.getPassword().contains(space) ||
                accountDTO.getName().contains(space) ||
                accountDTO.getQq().contains(space) ||
                accountDTO.getWechat().contains(space)) {
            return new ResultDTO(InformationEnumImpl.REGISTER_INFO_EMPTY_OR_SPACE.getCode(),
                    InformationEnumImpl.REGISTER_INFO_EMPTY_OR_SPACE.getMessage());
        }
        mailService.deleteCaptcha(accountDTO.getCaptcha());
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        return accountService.register(account);
    }
}
