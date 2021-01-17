package wiki.laona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wiki.laona.domain.Department;
import wiki.laona.mapper.DepartmentMapper;
import wiki.laona.service.DepartmentService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description:
 * @author: HuaiAnGG
 * @create: 2021-01-16 16:36
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门信息
     *
     * @return 部门信息列表
     */
    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.selectAll();
    }
}
