package wiki.laona.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huaian
 */
@Setter
@Getter
@ToString
public class Menu {
    private Long id;

    private String text;

    private String url;

    private Menu parent;

    private Permission permission;

    private List<Menu> children = new ArrayList<>();
}