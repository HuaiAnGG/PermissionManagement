package wiki.laona.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: PermissionManagement
 * @description: 分页查询对象封装，由easyui 自动传过来
 * @author: HuaiAnGG
 * @create: 2021-01-17 17:30
 **/
@Getter
@Setter
@ToString
public class QueryVo {
    /**
     * 当前页
     */
    private int page;
    /**
     * 每页数据条数
     */
    private int rows;
    /**
     * 搜索关键字
     */
    private String keyword;
}
