package wiki.laona.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门
 */
@Setter
@Getter
@ToString
public class Department {
    private Long id;

    private String name;
}