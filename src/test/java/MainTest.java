import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main_failsIfExecutionTimeExceeds22Seconds() {
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException("main runs over 22 seconds");
        }
    }
}
