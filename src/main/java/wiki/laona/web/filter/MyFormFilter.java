package wiki.laona.web.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import wiki.laona.domain.AjaxRes;
import wiki.laona.util.Loggers;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @program: PermissionManagement
 * @description: 表单过滤器
 * @author: HuaiAnGG
 * @create: 2021-01-21 15:24
 **/
public class MyFormFilter extends FormAuthenticationFilter {

    /**
     * 认证成功
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response) throws Exception {
        Loggers.info("MyFormFilter.onLoginSuccess");
        response.setCharacterEncoding("utf-8");
        AjaxRes resp = new AjaxRes();
        resp.setSuccess(true);
        resp.setMsg("登录成功");
        String jsonStr = new ObjectMapper().writeValueAsString(resp);
        response.getWriter().print(jsonStr);
        return false;
    }

    /**
     * 认证失败
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e,
                                     ServletRequest request,
                                     ServletResponse response) {
        AjaxRes resp = new AjaxRes();
        resp.setSuccess(false);
        // 获取异常参数
        if (e != null) {
            String name = e.getClass().getName();
            if (name.equals(UnknownAccountException.class.getName())) {
                resp.setMsg("账号不存在");
            } else if (name.equals(IncorrectCredentialsException.class.getName())) {
                resp.setMsg("密码错误");
            } else {
                resp.setMsg("未知异常");
            }
            try {
                response.setCharacterEncoding("utf-8");
                String jsonStr = new ObjectMapper().writeValueAsString(resp);
                response.getWriter().print(jsonStr);
            } catch (IOException jsonProcessingException) {
                jsonProcessingException.printStackTrace();
            }
        }
        return false;
    }


    
}
