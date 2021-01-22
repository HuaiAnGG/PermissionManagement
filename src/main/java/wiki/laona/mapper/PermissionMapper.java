package wiki.laona.mapper;

import java.util.List;

import wiki.laona.domain.Permission;

/**
 * @author huaian
 */
public interface PermissionMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long pid);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    /**
     * 根据角色 id 获取所有权限
     *
     * @param rid 角色id
     * @return 权限列表
     */
    List<Permission> selectPermissionByRid(Long rid);
}