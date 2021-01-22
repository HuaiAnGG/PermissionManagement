package wiki.laona.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import wiki.laona.domain.Role;

/**
 * @author huaian
 */
public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    Role selectByPrimaryKey(Long rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /**
     * 保存角色和权限关系
     *
     * @param rid 角色id
     * @param pid 权限id
     */
    void insertRoleAndPermissionRel(@Param("rid") Long rid, @Param("pid") Long pid);

    /**
     * 根据角色id 删除所有关系表中的数据
     *
     * @param rid 角色id
     */
    void deleteRoleAndPermissionRelByRid(Long rid);

    /**
     * 根据员工id 获取角色对应角色
     *
     * @param id 员工id
     * @return 角色列表rid
     */
    List<Long> selectRoleByEmployeeId(Long id);

    /**
     * 删除角色和用户关系
     * @param rid 角色id
     */
    void deleteRoleAndEmployeeRelByRid(Long rid);
}