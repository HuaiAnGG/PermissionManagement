package wiki.laona.service;

import wiki.laona.domain.Department;
import wiki.laona.domain.Employee;
import wiki.laona.domain.PageListRes;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 员工服务接口
 * @author: HuaiAnGG
 * @create: 2021-01-16 13:40
 **/
public interface EmployeeService {

    /**
     * 查询所有员工
     *
     * @return {@linkplain PageListRes} 分页结果集
     */
    PageListRes getAllEmployee();

    /**
     * 保存员工信息
     *
     * @param employee 员工信息
     */
    void save(Employee employee);

    /**
     * 更新员工信息
     *
     * @param employee 员工信息
     */
    void updateEmployee(Employee employee);

    /**
     * 更新员工状态信息
     * @param id 员工 id
     */
    void updateEmployeeState(Long id);
}
