package wiki.laona.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.domain.AjaxRes;
import wiki.laona.domain.Menu;
import wiki.laona.domain.PageListRes;
import wiki.laona.domain.QueryVo;
import wiki.laona.mapper.MenuMapper;
import wiki.laona.service.MenuService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description:
 * @author: HuaiAnGG
 * @create: 2021-01-22 20:29
 **/
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取所有菜单列表
     *
     * @param vo 分页查询 vo
     * @return 返回分页数据
     */
    @Override
    public PageListRes getAllMenuList(QueryVo vo) {
        // 分页查询
        Page<Menu> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        // 查询所有菜单列表
        List<Menu> menus = menuMapper.selectAll();
        // 分页数据返回
        PageListRes res = new PageListRes();
        res.setRows(menus);
        res.setTotal(page.getTotal());
        return res;
    }

    /**
     * 获取所有菜单父类
     *
     * @return 菜单列表
     */
    @Override
    public List<Menu> getMenuParentList() {
        return menuMapper.selectAll();
    }

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return
     */
    @Override
    public AjaxRes saveMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuMapper.insert(menu);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("菜单保存成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("菜单保存失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }

    /**
     * 更新菜单信息
     *
     * @param menu 菜单信息
     */
    @Override
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();

        // 新添加或者更改的数据的子菜单不能是当前数据的父级id
        if (menu.getParent() != null) {
            /**
             * 找出自己的子类
             */
            Long parentId = menuMapper.selectParentByPid(menu.getParent().getId());
            if (menu.getId().equals(parentId)) {
                ajaxRes.setSuccess(false);
                ajaxRes.setMsg("当前菜单的子类不能是其父菜单");
                return ajaxRes;
            }
        }
        // 更新
        try {
            menuMapper.updateByPrimaryKey(menu);
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
     *
     * @param id
     */
    @Override
    public AjaxRes deleteMenuById(long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuMapper.updateMenuRelByMid(id);
            menuMapper.deleteByPrimaryKey(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("删除成功");
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
            e.printStackTrace();
        }
        return ajaxRes;
    }

    /**
     * 获取属性菜单
     * @return 菜单数据
     */
    @Override
    public List<Menu> getTreeMenu() {
        return menuMapper.getTreeMenu();
    }
}
