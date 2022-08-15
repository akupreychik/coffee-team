package by.kupreychik.qc.coffee.dto.error;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorDTO {
    private String code;
    private String defaultText;
    private List<String> formatValues;

}
