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
}