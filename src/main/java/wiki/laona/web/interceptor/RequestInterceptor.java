package wiki.laona.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import wiki.laona.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: PermissionManagement
 * @description: 请求拦截器
 * @author: HuaiAnGG
 * @create: 2021-01-25 13:50
 **/
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Loggers.info("来到了拦截器");
        RequestUtils.setRequest(request);
        return true;
    }
}
