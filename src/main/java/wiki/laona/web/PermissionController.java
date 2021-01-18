package wiki.laona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.laona.domain.Permission;
import wiki.laona.service.PermissionService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 权限控制器
 * @author: HuaiAnGG
 * @create: 2021-01-18 13:58
 **/
@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/permissionList")
    @ResponseBody
    public List<Permission> getAllPermissionList() {
        return permissionService.getAllPermission();
    }

    @RequestMapping("/getPermissionByRid")
    @ResponseBody
    public List<Permission> getPermissionByRid(Long rid) {
        return permissionService.getAllPermissionById(rid);
    }
}
