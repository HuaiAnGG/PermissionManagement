package wiki.laona.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.laona.domain.*;
import wiki.laona.service.MenuService;

import java.util.List;

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
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuService.saveMenu(menu);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("菜单保存成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("菜单保存失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuService.updateMenu(menu);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }

    /**
     * 根据id 删除菜单信息
     * @param id 菜单id
     * @return
     */
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public AjaxRes deleteMenu(long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuService.deleteMenuById(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }
}
