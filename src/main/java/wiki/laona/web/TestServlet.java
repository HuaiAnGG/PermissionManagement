package wiki.laona.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

/**
 * @program: PermissionManagement
 * @description: 测试 servlet
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:17
 **/
@Controller
public class TestServlet extends HttpServlet {

    @RequestMapping("/test")
    public String test() {
        System.out.println("TestServlet.test");
        return "success";
    }
}
