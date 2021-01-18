package wiki.laona.service;

import wiki.laona.domain.Permission;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 权限 service
 * @author: HuaiAnGG
 * @create: 2021-01-18 14:00
 **/
public interface PermissionService {

    /**
     * 获取所有的权限
     * @return 所有权限
     */
    List<Permission> getAllPermission();

    /**
     * 根据角色 id 获取所有权限
     * @param rid 角色id
     * @return 权限列表
     */
    List<Permission> getAllPermissionById(Long rid);
}
