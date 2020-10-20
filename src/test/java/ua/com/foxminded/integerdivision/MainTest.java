package ua.com.foxminded.integerdivision;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import ua.com.foxminded.integerdivision.container.Context;
import ua.com.foxminded.integerdivision.text.LineFormatter;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOutput = System.out;
    private final PrintStream originalErr = System.err;

    //Main main = new Main();
    Context context = new Context();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOutput);
        System.setErr(originalErr);
        Main.setLineFormatter(context.getObject(LineFormatter.class));
    }

    @Test
    void main_ShouldReturnString_WhenInputTwoIntegers() {
        String[] arguments = { "0", "7" };
        LineFormatter mockedLineCreator = mock(LineFormatter.class);
        Main.setLineFormatter(mockedLineCreator);
        Main.main(arguments);
        when(mockedLineCreator.divide(anyInt(), anyInt())).thenReturn("_0|7\n" +
                                                                      " 0|-\n" +
                                                                      " -|0\n" +
                                                                      " 0"
                                                                                );

        assertEquals("_0|7\n" +
                     " 0|-\n" +
                     " -|0\n" +
                     " 0",          mockedLineCreator.divide(0, 7));
    }

}
