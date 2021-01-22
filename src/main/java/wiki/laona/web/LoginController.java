package wiki.laona.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: PermissionManagementdef
 * @description: 登录控制器
 * @author: HuaiAnGG
 * @create: 2021-01-21 13:44
 **/
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "redirect:login.jsp";
    }
}
