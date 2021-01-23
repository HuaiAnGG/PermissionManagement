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

}