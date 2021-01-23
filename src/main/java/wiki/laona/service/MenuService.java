package wiki.laona.service;

import wiki.laona.domain.Menu;
import wiki.laona.domain.PageListRes;
import wiki.laona.domain.QueryVo;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 菜单服务接口
 * @author: HuaiAnGG
 * @create: 2021-01-22 20:29
 **/
public interface MenuService {

    /**
     * 获取所有菜单列表
     * @param vo 分页查询 vo
     * @return 返回分页数据
     */
    PageListRes getAllMenuList(QueryVo vo);

    /**
     * 获取所有菜单父类
     * @return 菜单列表
     */
    List<Menu> getMenuParentList();

    /**
     * 保存菜单
     * @param menu 菜单
     * @return
     */
    int saveMenu(Menu menu);

    /**
     * 更新菜单信息
     * @param menu 菜单信息
     */
    void updateMenu(Menu menu);

    /**
     * 根据id 删除菜单信息
     * @param id
     */
    void deleteMenuById(long id);
}
