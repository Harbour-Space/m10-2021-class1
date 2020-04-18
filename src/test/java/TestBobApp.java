import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class TestBobApp {
    @Test
    public void testApp() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        try {
            BobApp.main(null);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Bob is started.\nHi, this is an inlined Bob.\nBob is done.\n", bos.toString());

        // undo the binding in System
        System.setOut(originalOut);
    }
}
