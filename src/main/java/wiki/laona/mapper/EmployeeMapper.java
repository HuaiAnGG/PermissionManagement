package wiki.laona.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import wiki.laona.domain.Employee;
import wiki.laona.domain.QueryVo;

/**
 * @author huaian
 */
public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll(QueryVo vo);

    int updateByPrimaryKey(Employee record);

    void updateStateByPrimaryKey(Long id);

    /**
     * 往员工角色关系表中插入对应关系
     *
     * @param eid 员工id
     * @param rid 角色id
     */
    void insertEmployeeAndRoleRel(@Param("eid") Long eid, @Param("rid") Long rid);

    /**
     * 解除员工和角色关系外键
     *
     * @param eid 员工id
     */
    void deleteEmployeeRoleRelByEid(Long eid);

    /**
     * 通过用户名查询该用户
     * @param username 用户名
     * @return 用户
     */
    Employee getEmployeeByUsername(String username);

    /**
     * 根据客户id获取角色编号名称
     * @param eid 角色id
     * @return 角色列表
     */
    List<String> getRolesByEid(@Param("eid") Long eid);

    /**
     * 根据客户id 查询权限资源名称
     *
     * @param eid 客户id
     * @return 权限列表
     */
    List<String> getPermissionListByRid(@Param("eid") Long eid);
}