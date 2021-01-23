package wiki.laona.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public int saveMenu(Menu menu) {
        return menuMapper.insert(menu);
    }

    /**
     * 更新菜单信息
     *
     * @param menu 菜单信息
     */
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKey(menu);
    }

    /**
     * 根据id 删除菜单信息
     * @param id
     */
    @Override
    public void deleteMenuById(long id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
