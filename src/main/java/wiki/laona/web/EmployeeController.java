package wiki.laona.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import wiki.laona.domain.AjaxRes;
import wiki.laona.domain.Employee;
import wiki.laona.domain.PageListRes;
import wiki.laona.domain.QueryVo;
import wiki.laona.service.EmployeeService;
import wiki.laona.util.Loggers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @program: PermissionManagement
 * @description: 员工控制器
 * @author: HuaiAnGG
 * @create: 2021-01-15 21:45
 **/
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 返回 employee.jsp 页面
     *
     * @return 页面地址
     */
    @RequestMapping("/employee")
    /**
     * @RequiresPermissions 需要有对应的权限才能访问接口，需要配置 Shiro 注解扫描
     */
    @RequiresPermissions("employee:index")
    public String employee() {
        return "employee";
    }

    /**
     * 获取所有员工列表
     *
     * @ResponseBody 把返回实体转成 json 数据
     */
    @ResponseBody
    @RequestMapping("/employeeList")
    public PageListRes employeeList(QueryVo vo) {
        System.out.println("vo = " + vo);
        return employeeService.getAllEmployee(vo);
    }


    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee) {
        AjaxRes resp = new AjaxRes();
        try {
            employee.setState(true);
            employeeService.save(employee);
            resp.setSuccess(true);
            resp.setMsg("保存成功！");

        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMsg("保存失败");
            e.getStackTrace();
        }
        return resp;
    }

    @RequestMapping("/updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public AjaxRes updateEmployee(Employee employee) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateEmployee(employee);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功!");
        } catch (Exception ex) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
            ex.printStackTrace();
        }
        return ajaxRes;
    }

    @RequestMapping("/updateEmployeeState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateEmployeeState(Long id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateEmployeeState(id);
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("更新成功!");
        } catch (Exception ex) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
            ex.getStackTrace();
        }
        return ajaxRes;
    }

    /**
     * 处理 shiro 异常
     *
     * @param method 发生异常的方法
     */
    @ExceptionHandler(AuthorizationException.class)
    public void handlerShiroException(HandlerMethod method, HttpServletResponse response) throws IOException {
        // 没有权限就提示用户
        // 判断是不是 json请求，如果是则返回 json 给浏览器
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation != null) {
            // ajax 请求
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("您没有操作权限！");
            response.setCharacterEncoding("utf-8");
            String respStr = new ObjectMapper().writeValueAsString(ajaxRes);
            response.getWriter().println(respStr);
        } else {
            response.sendRedirect("withoutPermission.jsp");
        }
    }

    /**
     * 导出 Excel
     * <p>
     * 1. 从数据库中读取列表数据
     * 2. 创建 Excel 写到 Excel 中
     * 3. 响应给浏览器
     */
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
        // 1. 从数据库中读取列表数据
        QueryVo vo = new QueryVo();
        vo.setPage(1);
        vo.setRows(10);
        PageListRes pageListRes = employeeService.getAllEmployee(vo);
        List<Employee> employees = (List<Employee>) pageListRes.getRows();
        try {
            // 2. 创建 Excel 写到 Excel 中
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCreationHelper creationHelper = wb.getCreationHelper();
            // 创建 Excel 中的一页
            HSSFSheet sheet = wb.createSheet("员工数据");
            HSSFRow sheetRow = sheet.createRow(0);
            // 设置行的每一列数据
            sheetRow.createCell(0).setCellValue("编号");
            sheetRow.createCell(1).setCellValue("用户名");
            sheetRow.createCell(2).setCellValue("入职日期");
            sheetRow.createCell(3).setCellValue("电话");
            sheetRow.createCell(4).setCellValue("邮箱");
            HSSFRow employeeRow = null;
            // 取出每一个员工来去设置数据
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                int rowNum = i + 1;
                employeeRow = sheet.createRow(rowNum);
                employeeRow.createCell(0).setCellValue(employee.getId());
                employeeRow.createCell(1).setCellValue(employee.getUsername());
                // 格式化日期
                String inputTime = employee.getInputtime() == null ? "" :
                        new SimpleDateFormat().format(employee.getInputtime());
                employeeRow.createCell(2).setCellValue(inputTime);
                employeeRow.createCell(3).setCellValue(employee.getTel());
                employeeRow.createCell(4).setCellValue(employee.getEmail());
            }
            String fileName = "员工导出数据_" + System.currentTimeMillis() + ".xls";
            // 3. 响应给浏览器
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            String userAgent = request.getHeader("User-Agent");
            // 针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
            } else {
                //非IE浏览器：
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载员工数据模板
     *
     * @param request
     * @param response
     */
    @RequestMapping("/downloadExcelTemplate")
    @ResponseBody
    public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        FileInputStream fis = null;
        try {
            // 3. 响应给浏览器
            String fileName = new String("ExcelTemplate.xls".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            String userAgent = request.getHeader("User-Agent");
            // 针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
            } else {
                //非IE浏览器：
                fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            // 获取文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/static/ExcelTemplate.xls");
            // 获取文件流
            fis = new FileInputStream(realPath);
            // 写入到 response 中
            IOUtils.copy(fis, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 文件上传 controller
     * 需要配置文件上传解析器
     *
     * @param excel
     * @return
     */
    @RequestMapping("/uploadExcelFile")
    @ResponseBody
    public AjaxRes uploadExcelFile(MultipartFile excel) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            ajaxRes.setSuccess(true);
            ajaxRes.setMsg("导入成功");
            HSSFWorkbook workbook = new HSSFWorkbook(excel.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            // 获取工作表中的行数
            int rowNum = sheet.getLastRowNum();
            Row employeeRow = null;
            for (int i = 1; i <= rowNum; i++) {
                employeeRow = sheet.getRow(i);
                // long eid = (long) getCellValue(employeeRow.getCell(0));
                String username = (String) getCellValue(employeeRow.getCell(1));
                Date inputtime = (Date) getCellValue(employeeRow.getCell(2));
                String tel = String.valueOf(getCellValue(employeeRow.getCell(3)));
                String email = (String) getCellValue(employeeRow.getCell(4));
                Employee employee = new Employee();
                // employee.setId(eid);
                employee.setUsername(username);
                employee.setInputtime(inputtime);
                employee.setTel(tel);
                employee.setEmail(email);
                employeeService.save(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("导入失败");
        }
        return ajaxRes;
    }


    /**
     * 处理 Excel 中行数据
     *
     * @param cell
     * @return
     */
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
        }
        return cell;

    }
}
