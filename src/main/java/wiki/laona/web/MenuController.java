package wiki.laona.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: PermissionManagement
 * @description: 菜单管理
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:50
 **/
@Controller
public class MenuController {

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }
}
