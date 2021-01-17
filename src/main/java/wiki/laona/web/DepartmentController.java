package wiki.laona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.laona.domain.Department;
import wiki.laona.service.DepartmentService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 部门控制器
 * @author: HuaiAnGG
 * @create: 2021-01-16 16:27
 **/
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有部门信息
     *
     * @return 部门信息列表
     */
    @RequestMapping("/getDepartmentList")
    @ResponseBody
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

}
