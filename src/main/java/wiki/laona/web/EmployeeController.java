package wiki.laona.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import wiki.laona.domain.AjaxRes;
import wiki.laona.domain.Employee;
import wiki.laona.domain.PageListRes;
import wiki.laona.domain.QueryVo;
import wiki.laona.service.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    /**
     * @RequiresPermissions 需要有对应的权限才能访问接口，需要配置 Shiro 注解扫描
     */
    @RequiresPermissions("employee:index")
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
    public PageListRes employeeList(QueryVo vo) {
        System.out.println("vo = " + vo);
        return employeeService.getAllEmployee(vo);
    }


    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee) {
        AjaxRes resp = new AjaxRes();
        try {
            employee.setState(true);
            employeeService.save(employee);
            resp.setSuccess(true);
            resp.setMsg("保存成功！");

        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMsg("保存失败");
            e.getStackTrace();
        }
        return resp;
    }

    @RequestMapping("/updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public AjaxRes updateEmployee(Employee employee) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功!");
        } catch (Exception ex) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
            ex.getStackTrace();
        }
        return ajaxRes;
    }

    @RequestMapping("/updateEmployeeState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateEmployeeState(Long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateEmployeeState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功!");
        } catch (Exception ex) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
            ex.getStackTrace();
        }
        return ajaxRes;
    }

    /**
     * 处理 shiro 异常
     *
     * @param method 发生异常的方法
     */
    @ExceptionHandler(AuthorizationException.class)
    public void handlerShiroException(HandlerMethod method, HttpServletResponse response) throws IOException {
        // 没有权限就提示用户
        // 判断是不是 json请求，如果是则返回 json 给浏览器
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation != null) {
            // ajax 请求
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("您没有操作权限！");
            response.setCharacterEncoding("utf-8");
            String respStr = new ObjectMapper().writeValueAsString(ajaxRes);
            response.getWriter().println(respStr);
        } else {
            response.sendRedirect("withoutPermission.jsp");
        }
    }
}
