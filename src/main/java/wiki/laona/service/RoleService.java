package wiki.laona.service;

import wiki.laona.domain.PageListRes;
import wiki.laona.domain.QueryVo;
import wiki.laona.domain.Role;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 角色service
 * @author: HuaiAnGG
 * @create: 2021-01-17 20:43
 **/
public interface RoleService {

    /**
     * 查询所有角色权限
     * @param vo 查询 vo
     * @return 所有角色列表
     */
    PageListRes getAllRole(QueryVo vo);

    /**
     * 保存角色权限
     * @param role 角色实体
     */
    void saveRole(Role role);
}
