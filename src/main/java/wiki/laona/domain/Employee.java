package wiki.laona.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 员工实体
 * @author huaian
 */
@ToString
@Setter
@Getter
public class Employee {
    private Long id;

    private String username;

    private String password;

    /**
     * 入职时间
     * @ JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      转成实体时，规则，时区
     * @ DateTimeFormat(pattern = "yyyy-MM-dd")                      表单提交时候规则
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    private String tel;

    private String email;

    private Boolean state;

    private Boolean admin;

    private Department department;
}