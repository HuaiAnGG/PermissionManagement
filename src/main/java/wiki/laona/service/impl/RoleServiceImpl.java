package wiki.laona.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.domain.PageListRes;
import wiki.laona.domain.Permission;
import wiki.laona.domain.QueryVo;
import wiki.laona.domain.Role;
import wiki.laona.mapper.PermissionMapper;
import wiki.laona.mapper.RoleMapper;
import wiki.laona.service.RoleService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 角色 service 实现类
 * @author: HuaiAnGG
 * @create: 2021-01-17 20:45
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有角色权限
     *
     * @param vo 查询 vo
     * @return 所有角色列表
     */
    @Override
    public PageListRes getAllRole(QueryVo vo) {
        // 分页查询
        Page<Role> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Role> roleList = roleMapper.selectAll();
        PageListRes pageListRes = new PageListRes();
        pageListRes.setRows(roleList);
        pageListRes.setTotal(page.getTotal());
        return pageListRes;
    }

    /**
     * 保存角色权限
     *
     * @param role 角色实体
     */
    @Override
    public void saveRole(Role role) {
        // 保存角色
        roleMapper.insert(role);
        // 保存权限
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(), permission.getPid());
        }
    }

    /**
     * 更新角色权限
     *
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        roleMapper.deleteRoleAndPermissionRelByRid(role.getRid());
        roleMapper.updateByPrimaryKey(role);
        // 保存权限
        for (Permission permission : role.getPermissions()) {
            roleMapper.insertRoleAndPermissionRel(role.getRid(), permission.getPid());
        }
    }

    /**
     * 删除角色权限和关系
     *
     * @param rid 角色 id
     */
    @Override
    public void deleteRoleByRid(Long rid) {
        // 删除关系
        roleMapper.deleteRoleAndPermissionRelByRid(rid);
        // 根据 rid 删除角色
        roleMapper.deleteByPrimaryKey(rid);
    }

    /**
     * 获取所有角色列表
     *
     * @return
     */
    @Override
    public List<Role> roleList() {
        return roleMapper.selectAll();
    }

    /**
     * 根据员工id 获取角色对应角色
     *
     * @param id 员工id
     * @return 角色列表
     */
    @Override
    public List<Long> getRoleByEid(Long id) {
        return roleMapper.selectRoleByEmployeeId(id);
    }

}
