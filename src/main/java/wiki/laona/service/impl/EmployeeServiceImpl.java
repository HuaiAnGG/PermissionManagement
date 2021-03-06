package wiki.laona.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.domain.*;
import wiki.laona.mapper.EmployeeMapper;
import wiki.laona.service.EmployeeService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: employeeService  接口实现类
 * @author: HuaiAnGG
 * @create: 2021-01-16 13:42
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * 注入 employeeMapper
     */
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageListRes getAllEmployee(QueryVo vo) {
        // 分页查询
        Page<Employee> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        // 调用 mapper 查询数据库
        List<Employee> employees = employeeMapper.selectAll(vo);
        // 封装成 PageListRes 实体
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    /**
     * 保存员工信息
     *
     * @param employee 员工信息
     */
    @Override
    public void save(Employee employee) {
        String password = employee.getPassword();
        if (password != null) {
            // 对密码进行加密
            Md5Hash pwdMd5Hash = new Md5Hash(employee.getPassword(), employee.getUsername(), 3);
            employee.setPassword(pwdMd5Hash.toString());
        } else {
            // 设置默认密码
            Md5Hash pwdMd5Hash = new Md5Hash("123 ", employee.getUsername(), 3);
            employee.setPassword(pwdMd5Hash.toString());
        }
        // 保存角色
        employeeMapper.insert(employee);
        // 保存员工、角色关系
        for (Role role : employee.getRoles()) {
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(), role.getRid());
        }
    }

    /**
     * 更新员工信息
     *
     * @param employee 员工信息
     */
    @Override
    public void updateEmployee(Employee employee) {
        // 删除员工_角色外键约束
        employeeMapper.deleteEmployeeRoleRelByEid(employee.getId());
        // 更新员工信息
        employeeMapper.updateByPrimaryKey(employee);
        // 添加员工角色关系
        for (Role role : employee.getRoles()) {
            employeeMapper.insertEmployeeAndRoleRel(employee.getId(), role.getRid());
        }
    }

    /**
     * 更新员工状态信息
     *
     * @param id 员工 id
     */
    @Override
    public void updateEmployeeState(Long id) {
        employeeMapper.updateStateByPrimaryKey(id);
    }

    @Override
    public Employee getEmployeeByUsername(String username) {

        return employeeMapper.getEmployeeByUsername(username);
    }

    /**
     * 根据客户id获取角色编号名称
     *
     * @param eid 角色id
     * @return 角色列表
     */
    @Override
    public List<String> getRoleListByEid(Long eid) {
        return employeeMapper.getRolesByEid(eid);
    }

    /**
     * 根据客户id 查询权限资源名称
     *
     * @param id 客户id
     * @return 权限列表
     */
    @Override
    public List<String> getPermissionListByRid(Long id) {
        return employeeMapper.getPermissionListByRid(id);
    }

}
