package wiki.laona.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: PermissionManagement
 * @description: request 工具类
 * @author: HuaiAnGG
 * @create: 2021-01-25 13:53
 **/
public class RequestUtils {

    /**
     * 全局本地请求线程变量
     */
    public static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    /**
     * 获取请求方法
     * @return HttpServletRequest request
     */
    public static HttpServletRequest getRequestInstance() {
        return requestThreadLocal.get();
    }

    public static void setRequest(HttpServletRequest request) {
        requestThreadLocal.set(request);
    }
}
