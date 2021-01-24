package wiki.laona.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.laona.domain.*;
import wiki.laona.service.MenuService;

import java.util.List;
import java.util.ListIterator;

/**
 * @program: PermissionManagement
 * @description: 菜单管理
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:50
 **/
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    /**
     * 获取所有的菜单列表信息
     *
     * @param vo 查询 vo
     * @return 分页信息
     */
    @RequestMapping("/menuList")
    @ResponseBody
    public PageListRes menuList(QueryVo vo) {
        return menuService.getAllMenuList(vo);
    }

    @RequestMapping("/getMenuParentList")
    @ResponseBody
    public List<Menu> getMenuParentList() {
        return menuService.getMenuParentList();
    }

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return 菜单列表
     */
    @RequestMapping("/saveMenu")
    @ResponseBody
    public AjaxRes saveMenu(Menu menu) {
        return menuService.saveMenu(menu);
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public AjaxRes updateMenu(Menu menu) {
        return menuService.updateMenu(menu);
    }

    /**
     * 根据id 删除菜单信息
     *
     * @param id 菜单id
     * @return
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public AjaxRes deleteMenu(long id) {
        return menuService.deleteMenuById(id);
    }

    @RequestMapping("/getTreeMenu")
    @ResponseBody
    public List<Menu> getTreeMenu() {
        List<Menu> treeMenu = menuService.getTreeMenu();
        /**
         * 判断是否有菜单，没有就移除对应的权限
         */
        //  获取对象 判断用户是否是管理员，是管理员就不需要做判断
        Subject subject = SecurityUtils.getSubject();
        // 当前用户
        Employee employee = (Employee) subject.getPrincipal();
        if (!employee.getAdmin()) {
            // 校验权限
            checkPermission(treeMenu);
        }

        return treeMenu;
    }

    /**
     * 校验权限
     *
     * @param menus
     */
    private void checkPermission(List<Menu> menus) {
        //     获取主体
        Subject subject = SecurityUtils.getSubject();
        //    遍历所有菜单及子菜单
        ListIterator<Menu> iterator = menus.listIterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            Permission permission = menu.getPermission();
            if (permission != null) {
                String presource = permission.getPresource();
                if (!subject.isPermitted(presource)) {
                    //     当前遍历的菜单移除集合中的元素
                    iterator.remove();
                    continue;
                }
            }
            //    子菜单也需要做权限校验
            List<Menu> menuChildren = menu.getChildren();
            if (menuChildren.size() > 0) {
                checkPermission(menuChildren);
            }
        }

    }
}
