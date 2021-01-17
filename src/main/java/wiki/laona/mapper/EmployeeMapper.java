package wiki.laona.mapper;

import java.util.List;
import wiki.laona.domain.Employee;
import wiki.laona.domain.QueryVo;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll(QueryVo vo);

    int updateByPrimaryKey(Employee record);

    void updateStateByPrimaryKey(Long id);
}