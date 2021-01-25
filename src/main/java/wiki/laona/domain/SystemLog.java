package wiki.laona.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class SystemLog {
    private Long id;

    private Date optime;

    private String ip;

    private String func;

    private String params;
}