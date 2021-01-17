package wiki.laona.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: PermissionManagement
 * @description: ajax 结果返回
 * @author: HuaiAnGG
 * @create: 2021-01-16 17:17
 **/
@Setter
@Getter
@ToString
public class AjaxRes {
    private boolean success;
    private String msg;
}
