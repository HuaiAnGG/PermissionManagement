package wiki.laona.web.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import wiki.laona.domain.Employee;
import wiki.laona.service.EmployeeService;
import wiki.laona.util.Loggers;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 员工认证 realm
 * @author: HuaiAnGG
 * @create: 2021-01-21 14:06
 **/
public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 认证
     *
     * @param token token
     * @return info
     * @throws AuthenticationException {@linkplain AuthenticationException}
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Loggers.info("EmployeeRealm.doGetAuthenticationInfo 来到了认证");
        String username = (String) token.getPrincipal();
        Loggers.info("用户名：" + username);
        /**
         * 到数据库查询是否有该用户
         */
        Employee employee = employeeService.getEmployeeByUsername(username);
        System.out.println("employee = " + employee);
        if (employee == null) {
            return null;
        }
        /**
         * 认证用户
         * 主体，正确的密码，盐，当前的 realm 名称
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                employee,
                employee.getPassword(),
                ByteSource.Util.bytes(employee.getUsername()),
                this.getName()
        );
        return info;
    }

    /**
     * 授权
     * web  doGetAuthorizationInfo 什么时候调用
     * 1.发现访问路径对应的方法上面 有授权注解  就会调用doGetAuthorizationInfo
     * 2.页面当中有授权标签  也会调用doGetAuthorizationInfo
     *
     * @param principal 主体
     * @return info
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        Loggers.info("EmployeeRealm.doGetAuthorizationInfo 来到了授权");
        /**
         * 1. 获取用户信息
         */
        Employee employee = (Employee) principal.getPrimaryPrincipal();
        /**
         * 根据当前用户查询角色和权限
         */
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        if (employee.getAdmin()) {
            permissions.add("*:*");
        }else {
            // 查询角色
            roles = employeeService.getRoleListByEid(employee.getId());
            // 查询权限
            permissions = employeeService.getPermissionListByRid(employee.getId());
        }
        /**
         * 给授权信息
         */
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

}
