package wiki.laona.service;

import wiki.laona.domain.PageListRes;

/**
 * @program: PermissionManagement
 * @description: 员工服务接口
 * @author: HuaiAnGG
 * @create: 2021-01-16 13:40
 **/
public interface EmployeeService {

    /**
     * 查询所有员工
     * @return {@linkplain PageListRes} 分页结果集
     */
    PageListRes getAllEmployee();
}
