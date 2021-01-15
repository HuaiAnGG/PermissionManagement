package wiki.laona.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: PermissionManagement
 * @description: 员工控制器
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:45
 **/
@Controller
public class EmployeeController {

    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }
}
