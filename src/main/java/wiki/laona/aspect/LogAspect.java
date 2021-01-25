package wiki.laona.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.domain.SystemLog;
import wiki.laona.mapper.SystemLogMapper;
import wiki.laona.util.Loggers;
import wiki.laona.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @program: PermissionManagement
 * @description: 日志切面
 * @author: HuaiAnGG
 * @create: 2021-01-24 16:38
 **/
// @Transactional
public class LogAspect {

    @Autowired
    private SystemLogMapper systemLogMapper;

    /**
     * 写入操作日志
     */
    public void writeLog(JoinPoint joinPoint) throws JsonProcessingException {
        // 设置时间
        SystemLog systemLog = new SystemLog();
        systemLog.setOptime(new Date());
        /**
         * 设置 ip   request 对象
         * 拦截器  拦截
         */
        HttpServletRequest request = RequestUtils.getRequestInstance();
        if (request != null) {
            String remoteAddr = request.getRemoteAddr();
            systemLog.setIp(remoteAddr);
        }
        // 获取方法名称
        String name = joinPoint.getTarget().getClass().getName();
        String signature = joinPoint.getSignature().getName();
        String func = name + ":" + signature;
        systemLog.setFunc(func);
        // 获取方法参数
        String args = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        systemLog.setParams(args);
        Loggers.info(systemLog.toString());
        // 插入数据库中
        systemLogMapper.insert(systemLog);
    }
}
