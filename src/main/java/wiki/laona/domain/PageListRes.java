package wiki.laona.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: PermissionManagement
 * @description: employee页面返回结果
 * @author: HuaiAnGG
 * @create: 2021-01-16 13:48
 **/
@Setter
@Getter
@ToString
public class PageListRes {
    private Long total;
    private List<?> rows = new ArrayList<>();
}
