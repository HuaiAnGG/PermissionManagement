package wiki.laona.web;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.laona.domain.AjaxRes;
import wiki.laona.domain.Department;
import wiki.laona.domain.Employee;
import wiki.laona.domain.PageListRes;
import wiki.laona.service.EmployeeService;

import java.util.List;

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
     * 返回 employee.jsp 页面
     *
     * @return 页面地址
     */
    @RequestMapping("/employee")
    public String employee() {
        return "employee";
    }

    /**
     * 获取所有员工列表
     *
     * @ResponseBody 把返回实体转成 json 数据
     */
    @ResponseBody
    @RequestMapping("/employeeList")
    public PageListRes employeeList() {
        return employeeService.getAllEmployee();
    }


    @RequestMapping("/saveEmployee")
    @ResponseBody
    public AjaxRes saveEmployee(Employee employee) {
        AjaxRes resp = new AjaxRes();
        try {
            System.out.println("employee = " + employee);
            employee.setState(true);
            employeeService.save(employee);
            resp.setSuccess(true);
            resp.setMsg("保存成功！");
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMsg("保存失败！" + e.getMessage());
        }
        return resp;
    }

    @RequestMapping("/updateEmployee")
    @ResponseBody
    public AjaxRes updateEmployee(Employee employee) {
        AjaxRes ajaxRes = new AjaxRes();
        try{
            employeeService.updateEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功!");
        }catch (Exception ex) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败" + ex.getMessage());
        }
        return ajaxRes;
    }
}
