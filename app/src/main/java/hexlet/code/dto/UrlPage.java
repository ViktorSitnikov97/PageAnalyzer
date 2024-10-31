package hexlet.code.dto;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class UrlPage extends BasePage {
    private Url url;
    private List<UrlCheck> checks;
    public UrlPage(Url url) {
        this.url = url;
    }
}
