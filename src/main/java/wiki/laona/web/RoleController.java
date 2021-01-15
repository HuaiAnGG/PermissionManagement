package wiki.laona.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: PermissionManagement
 * @description: 角色控制器
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:47
 **/
@Controller
public class RoleController {

    @RequestMapping("/role")
    public String role() {
        return "role";
    }
}
