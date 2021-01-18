package wiki.laona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiki.laona.domain.Permission;
import wiki.laona.mapper.PermissionMapper;
import wiki.laona.service.PermissionService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description:
 * @author: HuaiAnGG
 * @create: 2021-01-18 14:01
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 获取所有的权限
     * @return
     */
    @Override
    public List<Permission> getAllPermission() {
        List<Permission> permissionList = permissionMapper.selectAll();
        return permissionList;
    }
}
