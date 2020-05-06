package icu.thyself.cloudlesson.service;

/**
 * @author luoyelun
 * @date 2020/5/4 12:21
 */

public interface MailService {
    String getCaptcha();

    void sendMail(String captcha, String target);

    boolean check(String captcha);

    void deleteCaptcha(String captcha);
}
