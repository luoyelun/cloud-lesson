package icu.thyself.cloudlesson.config;

import com.alibaba.fastjson.JSON;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.enums.InformationEnumImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luoyelun
 * @date 2020/5/4 1:38
 * 自定义登录成功处理
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

    }
}
