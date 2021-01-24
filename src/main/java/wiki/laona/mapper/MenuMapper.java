package wiki.laona.mapper;

import java.util.List;
import wiki.laona.domain.Menu;

/**
 * @author huaian
 */
public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    /**
     * 查找该数据的子类 id
     * @param id
     * @return
     */
    Long selectParentByPid(Long id);

    /**
     * 删除菜单与父类对应关系
     * @param id 菜单id
     */
    void updateMenuRelByMid(long id);

    /**
     * 获取树形菜单
     * @return 菜单数据
     */
    List<Menu> getTreeMenu();
}