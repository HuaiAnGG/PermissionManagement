package wiki.laona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.laona.domain.PageListRes;
import wiki.laona.service.EmployeeService;

/**
 * @program: PermissionManagement
 * @description: 员工控制器
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:45
 **/
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 获取所有员工列表
     * @ResponseBody 把返回实体转成 json 数据
     */
    @ResponseBody
    @RequestMapping("/employeeList")
    public PageListRes employeeList() {
        return employeeService.getAllEmployee();
    }

    /**
     * 返回 employee.jsp 页面
     * @return 页面地址
     */
    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }
}
