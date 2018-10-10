import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestGetStream {
    static final Logger myLogger = Logger.getLogger(Main.class);

    private GetStream getStream;
    private String string = "abcde";
    private String[] source;

    @BeforeAll
    static void initTest() {
        myLogger.info("Init all for TestGetStream");
    }

    @Test
    void testINputStream() {
        myLogger.info("Test passed for TestGetStream");
    }

}
