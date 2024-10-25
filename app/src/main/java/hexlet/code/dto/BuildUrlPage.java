package hexlet.code.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class BuildUrlPage extends BasePage {
    private String name;
}
