package wiki.laona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.laona.domain.AjaxRes;
import wiki.laona.domain.PageListRes;
import wiki.laona.domain.QueryVo;
import wiki.laona.domain.Role;
import wiki.laona.service.RoleService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 角色控制器
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:47
 **/
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/role")
    public String role() {
        return "role";
    }

    @RequestMapping("/roleList")
    @ResponseBody
    public PageListRes roleList(QueryVo vo) {
        // 调用业务层，查询角色列表
        return roleService.getAllRole(vo);
    }

    @RequestMapping("/saveRole")
    @ResponseBody
    public AjaxRes saveRole(Role role) {
        AjaxRes resp = new AjaxRes();
        try {
            // 调用业务层，保存角色
            roleService.saveRole(role);
            resp.setSuccess(true);
            resp.setMsg("保存成功！");
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMsg("保存失败！" + e.getMessage());
        }
        return resp;
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public AjaxRes updateRole(Role role) {
        AjaxRes resp = new AjaxRes();
        try {
            // 调用业务层，保存角色
            roleService.updateRole(role);
            resp.setSuccess(true);
            resp.setMsg("更新成功！");
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMsg("更新失败！" + e.getMessage());
        }
        return resp;
    }


    @RequestMapping("/deleteRole")
    @ResponseBody
    public AjaxRes deleteRole(Long rid) {
        AjaxRes resp = new AjaxRes();
        try {
            // 调用业务层，删除角色
            roleService.deleteRoleByRid(rid);
            resp.setSuccess(true);
            resp.setMsg("更新角色成功！");
        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMsg("更新角色失败！" + e.getMessage());
        }
        return resp;
    }

    @RequestMapping("/allRole")
    @ResponseBody
    public List<Role> roleList() {
        return roleService.roleList();
    }

    @RequestMapping("/getEmployeeRoleByEid")
    @ResponseBody
    public List<Long> getEmployeeRoleByEid(Long id) {
        return roleService.getRoleByEid(id);
    }
}
