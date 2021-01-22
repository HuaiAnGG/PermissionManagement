package wiki.laona.util;

import org.apache.log4j.Logger;

/**
 * @program: PermissionManagement
 * @description: 日志工具类
 * @author: HuaiAnGG
 * @create: 2021-01-21 14:59
 **/
public class Loggers {
    private static final Logger LOGGER = Logger.getLogger("laonaLog");

    public static void info(String info) {
        LOGGER.info(info);
    }

    public static void debug(String debug){
        LOGGER.debug(debug);
    }
    public static void error(String error) {
        LOGGER.error(error);
    }

}
