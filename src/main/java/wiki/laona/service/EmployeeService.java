package wiki.laona.service;

import org.apache.ibatis.annotations.Param;
import wiki.laona.domain.Department;
import wiki.laona.domain.Employee;
import wiki.laona.domain.PageListRes;
import wiki.laona.domain.QueryVo;

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
    PageListRes getAllEmployee(QueryVo v0);

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
     *
     * @param id 员工 id
     */
    void updateEmployeeState(Long id);

    /**
     * 通过用户名查询该用户
     * @param username 用户名
     * @return 用户 or null
     */
    Employee getEmployeeByUsername(String username);

    /**
     * 根据客户id获取角色编号名称
     * @param eid 角色id
     * @return 角色列表
     */
    List<String> getRoleListByEid(Long eid);

    /**
     * 根据客户id 查询权限资源名称
     * @param id 客户id
     * @return 权限列表
     */
    List<String> getPermissionListByRid(Long id);
}
