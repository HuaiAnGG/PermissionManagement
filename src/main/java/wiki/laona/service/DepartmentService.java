package wiki.laona.service;

import wiki.laona.domain.Department;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 部门信息service
 * @author: HuaiAnGG
 * @create: 2021-01-16 16:31
 **/
public interface DepartmentService {

    /**
     * 获取所有部门信息
     *
     * @return 部门信息列表
     */
    List<Department> getAllDepartment();
}
