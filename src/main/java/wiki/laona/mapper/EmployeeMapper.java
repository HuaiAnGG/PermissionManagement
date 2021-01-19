package wiki.laona.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import wiki.laona.domain.Employee;
import wiki.laona.domain.QueryVo;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll(QueryVo vo);

    int updateByPrimaryKey(Employee record);

    void updateStateByPrimaryKey(Long id);

    /**
     * 往员工角色关系表中插入对应关系
     * @param eid 员工id
     * @param rid 角色id
     */
    void insertEmployeeAndRoleRel(@Param("eid") Long eid, @Param("rid") Long rid);

    /**
     * 解除员工和角色关系外键
     * @param eid 员工id
     */
    void deleteEmployeeRoleRelByEid(Long eid);
}