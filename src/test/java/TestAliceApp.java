import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class TestAliceApp {
    @Test
    public void testApp() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            AliceApp.main(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Hi, this is Alice!\nAlice is done.\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }
}
