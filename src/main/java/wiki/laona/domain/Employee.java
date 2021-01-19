package wiki.laona.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private String tel;

    private String email;

    /**
     * 入职时间
     * @ JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")      转成实体时，规则，时区
     * @ DateTimeFormat(pattern = "yyyy-MM-dd")                      表单提交时候规则
     */
    @Nullable
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    private Boolean state;

    private Boolean admin;

    private Department department;

    private List<Role> roles = new ArrayList<>();
}