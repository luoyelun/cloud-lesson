package icu.thyself.cloudlesson.service;

import icu.thyself.cloudlesson.mapper.MailCheckMapper;
import icu.thyself.cloudlesson.model.MailCheck;
import icu.thyself.cloudlesson.model.MailCheckExample;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author luoyelun
 * @date 2020/5/4 12:23
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private MailCheckMapper mailCheckMapper;

    @Override
    public String getCaptcha() {
        String captcha = RandomStringUtils.randomAlphanumeric(10);
        MailCheck mailCheck = new MailCheck();
        mailCheck.setCaptcha(captcha);
        mailCheckMapper.insert(mailCheck);
        return captcha;
    }

    @Override
    public void sendMail(String captcha, String target) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("云课-注册验证码");
        mailMessage.setText("验证码:" + captcha);
        mailMessage.setFrom("120107936@qq.com");
        mailMessage.setTo(target);
        mailSender.send(mailMessage);
    }

    @Override
    public boolean check(String captcha) {
        MailCheckExample mailCheckExample = new MailCheckExample();
        mailCheckExample.createCriteria().andCaptchaEqualTo(captcha);
        List<MailCheck> mailChecks = mailCheckMapper.selectByExample(mailCheckExample);
        return mailChecks.size() > 0;
    }

    @Override
    public void deleteCaptcha(String captcha) {
        MailCheckExample mailCheckExample = new MailCheckExample();
        mailCheckExample.createCriteria().andCaptchaEqualTo(captcha);
        mailCheckMapper.deleteByExample(mailCheckExample);
    }
}
