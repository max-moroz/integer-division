package ua.com.foxminded.integerdivision.container;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.integerdivision.math.Divider;

import java.util.logging.Level;
import java.util.logging.Logger;

class ContextTest {

    Divider divider = new Divider();
    Context context = new Context();

    @Test
    void getObject_ShouldReturnObject_WhenInputClass() {
        assertSame(divider.getClass(), context.getObject(Divider.class).getClass());
    }

    @Test
    void getObject_ShouldThrowException() throws Exception {
        Exception mockedException = mock(InstantiationException.class);
        Logger mockedLogger = mock(Logger.class);

        class Dummy {

            public Dummy() throws Exception{
                throw mockedException;
            }
        }

        try {
            Dummy dummy = context.getObject(Dummy.class);
        } catch (Exception e){
            verify(mockedLogger, times(1)).log(Level.INFO, anyString());
        }
    }
}
