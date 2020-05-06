package icu.thyself.cloudlesson.config;

import com.alibaba.fastjson.JSON;
import icu.thyself.cloudlesson.dto.ResultDTO;
import icu.thyself.cloudlesson.enums.InformationEnumImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luoyelun
 * @date 2020/5/4 1:48
 * 自定义登录失败处理
 */
@Component
public class MyAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        //登录失败返回消息
        response.getWriter().write(JSON.toJSONString(new ResultDTO(InformationEnumImpl.LOGIN_FAIL.getCode(), InformationEnumImpl.LOGIN_FAIL.getMessage())));
    }
}
