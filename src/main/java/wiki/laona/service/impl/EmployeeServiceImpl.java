package wiki.laona.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.laona.domain.Employee;
import wiki.laona.domain.PageListRes;
import wiki.laona.mapper.EmployeeMapper;
import wiki.laona.service.EmployeeService;

import java.util.List;

/**
 * @program: PermissionManagement
 * @description: employeeService  接口实现类
 * @author: HuaiAnGG
 * @create: 2021-01-16 13:42
 **/
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * 注入 employeeMapper
     */
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public PageListRes getAllEmployee() {
        // 分页查询
        Page<Employee> page = PageHelper.startPage(1, 5);
        // 调用 mapper 查询数据库
        List<Employee> employees = employeeMapper.selectAll();
        // 封装成 PageListRes 实体
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }
}
