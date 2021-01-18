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
}
