package wiki.laona.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
}