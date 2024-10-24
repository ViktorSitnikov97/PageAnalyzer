package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;

public class TestCalculate {

    @Test
    public void calculate() throws URISyntaxException {
        var expected = 10;
        int a = 5;
        int b = 5;
        var actual = Main.calculate(a, b);
        Assertions.assertEquals(expected, actual);

    }
}
