package ua.com.foxminded.integerdivision.text;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.foxminded.integerdivision.math.Divider;


class LineFormatterTest {

     @InjectMocks
    LineFormatter lineFormatter;

   @Mock
    Divider mockedDivider;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test_divide_ShouldThrowException_WhenInputZeroDivider() {
        assertThrows(IllegalArgumentException.class, () -> lineFormatter.divide(5, 0));
    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputZeroDivider() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(0);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(1);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(0);

        assertEquals("_0|7\n" +
                              " 0|-\n" +
                              " -|0\n" +
                              " 0"           , lineFormatter.divide(0, 7));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputDividerLessThenDivisor() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(0);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(1);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(5);

        assertEquals("_5|7\n" +
                              " 0|-\n" +
                              " -|0\n" +
                              " 5"           , lineFormatter.divide(5, 7));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputDividerMoreThenDivisor() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(1);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(5);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(1);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(2);

        assertEquals("_7|5\n" +
                              " 5|-\n" +
                              " -|1\n" +
                              " 2"           , lineFormatter.divide(7, 5));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputDividerConsistsOfMoreThenOneFigureAndOneOperationOnly() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(1);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(250);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(1);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(4);

        assertEquals("_254|250\n" +
                              " 250|---\n" +
                              " ---|1\n" +
                              "   4"           , lineFormatter.divide(254, 250));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputNegativeDividerConsistsOfMoreThenOneFigureAndOneOperationOnly() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(-1);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(-250);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(1);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(-4);

        assertEquals("_-254|250\n" +
                              " -250|---\n" +
                              "  ---|-1\n" +
                              "   -4"           , lineFormatter.divide(-254, 250));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenDivisorIsNumberOne() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(123456);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(5).thenReturn(6);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(6);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(0).thenReturn(0).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(5).thenReturn(6).thenReturn(0);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(0).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(5).thenReturn(6);

        assertEquals("_123456|1\n" +
                              " 1     |------\n" +
                              " -     |123456\n" +
                              " _2\n" +
                              "  2\n" +
                              "  -\n" +
                              "  _3\n" +
                              "   3\n" +
                              "   -\n" +
                              "   _4\n" +
                              "    4\n" +
                              "    -\n" +
                              "    _5\n" +
                              "     5\n" +
                              "     -\n" +
                              "     _6\n" +
                              "      6\n" +
                              "      -\n" +
                              "      0"       , lineFormatter.divide(123456, 1));
    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenFirstSubtrahendIsLessThenDividendPart() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(1524);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(81).thenReturn(81).thenReturn(405).thenReturn(162).thenReturn(324);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(4);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(123).thenReturn(123).thenReturn(424).thenReturn(195).thenReturn(336).thenReturn(12);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(19).thenReturn(33).thenReturn(12);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(123).thenReturn(424).thenReturn(195).thenReturn(336);

        assertEquals("_123456|81\n" +
                              "  81   |----\n" +
                              "  --   |1524\n" +
                              " _424\n" +
                              "  405\n" +
                              "  ---\n" +
                              "  _195\n" +
                              "   162\n" +
                              "   ---\n" +
                              "   _336\n" +
                              "    324\n" +
                              "    ---\n" +
                              "     12"       , lineFormatter.divide(123456, 81));
    }


    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputBigNumbers() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(9080);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(729).thenReturn(729).thenReturn(648);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(2);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(735).thenReturn(735).thenReturn(649).thenReturn(18);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(729);

        assertEquals("_735498|81\n" +
                              " 729   |----\n" +
                              " ---   |9080\n" +
                              "  _649\n" +
                              "   648\n" +
                              "   ---\n" +
                              "     18"       , lineFormatter.divide(735498, 81));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputThreeFigureDivisor() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(14490);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(852).thenReturn(852).thenReturn(3408).thenReturn(3408).thenReturn(7668);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(4);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(1234).thenReturn(1234).thenReturn(3825).thenReturn(4176).thenReturn(7687).thenReturn(198);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(417).thenReturn(768).thenReturn(19);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(1234).thenReturn(3825).thenReturn(4176).thenReturn(7687);

        assertEquals("_12345678|852\n" +
                              "  852    |-----\n" +
                              "  ---    |14490\n" +
                              " _3825\n" +
                              "  3408\n" +
                              "  ----\n" +
                              "  _4176\n" +
                              "   3408\n" +
                              "   ----\n" +
                              "   _7687\n" +
                              "    7668\n" +
                              "    ----\n" +
                              "      198"    , lineFormatter.divide(12345678, 852));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputFiguresLikeInExample() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(19736);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(4).thenReturn(4).thenReturn(36).thenReturn(28).thenReturn(12).thenReturn(24);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(5);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(7).thenReturn(7).thenReturn(38).thenReturn(29).thenReturn(14).thenReturn(25).thenReturn(1);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(2).thenReturn(1).thenReturn(2).thenReturn(1);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(7).thenReturn(38).thenReturn(29).thenReturn(14).thenReturn(25);

        assertEquals("_78945|4\n" +
                              " 4    |-----\n" +
                              " -    |19736\n" +
                              "_38\n" +
                              " 36\n" +
                              " --\n" +
                              " _29\n" +
                              "  28\n" +
                              "  --\n" +
                              "  _14\n" +
                              "   12\n" +
                              "   --\n" +
                              "   _25\n" +
                              "    24\n" +
                              "    --\n" +
                              "     1"       , lineFormatter.divide(78945, 4));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputFiguresLikeInExampleWithNegativeDivisor() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(-19736);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(4).thenReturn(4).thenReturn(36).thenReturn(28).thenReturn(12).thenReturn(24);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(5);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(7).thenReturn(7).thenReturn(38).thenReturn(29).thenReturn(14).thenReturn(25).thenReturn(1);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(2).thenReturn(1).thenReturn(2).thenReturn(1);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(7).thenReturn(38).thenReturn(29).thenReturn(14).thenReturn(25);

        assertEquals("_78945|-4\n" +
                " 4    |------\n" +
                " -    |-19736\n" +
                "_38\n" +
                " 36\n" +
                " --\n" +
                " _29\n" +
                "  28\n" +
                "  --\n" +
                "  _14\n" +
                "   12\n" +
                "   --\n" +
                "   _25\n" +
                "    24\n" +
                "    --\n" +
                "     1"       , lineFormatter.divide(78945, -4));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputFiguresLikeInExampleWithNegativeDividend() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(-19736);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(-4).thenReturn(-4).thenReturn(-36).thenReturn(-28).thenReturn(-12).thenReturn(-24);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(5);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(-7).thenReturn(-7).thenReturn(-38).thenReturn(-29).thenReturn(-14).thenReturn(-25).thenReturn(-1);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(-2).thenReturn(-1).thenReturn(-2).thenReturn(-1);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(-7).thenReturn(-38).thenReturn(-29).thenReturn(-14).thenReturn(-25);

        assertEquals("_-78945|4\n" +
                " -4    |------\n" +
                "  -    |-19736\n" +
                "_-38\n" +
                " -36\n" +
                "  --\n" +
                " _-29\n" +
                "  -28\n" +
                "   --\n" +
                "  _-14\n" +
                "   -12\n" +
                "    --\n" +
                "   _-25\n" +
                "    -24\n" +
                "     --\n" +
                "     -1"       , lineFormatter.divide(-78945, 4));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenInputFiguresLikeInExampleWithBothNegativeInputs() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(19736);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(4).thenReturn(4).thenReturn(36).thenReturn(28).thenReturn(12).thenReturn(24);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(5);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(7).thenReturn(7).thenReturn(38).thenReturn(29).thenReturn(14).thenReturn(25).thenReturn(1);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(2).thenReturn(1).thenReturn(2).thenReturn(1);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(7).thenReturn(38).thenReturn(29).thenReturn(14).thenReturn(25);

        assertEquals("_78945|4\n" +
                " 4    |-----\n" +
                " -    |19736\n" +
                "_38\n" +
                " 36\n" +
                " --\n" +
                " _29\n" +
                "  28\n" +
                "  --\n" +
                "  _14\n" +
                "   12\n" +
                "   --\n" +
                "   _25\n" +
                "    24\n" +
                "    --\n" +
                "     1"       , lineFormatter.divide(-78945, -4));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenDividendStartsWithTheSameFiguresAsDivisorTwice() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(101035);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(25).thenReturn(25).thenReturn(25).thenReturn(75).thenReturn(125);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(4);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(25).thenReturn(25).thenReturn(25).thenReturn(89).thenReturn(144).thenReturn(19);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(0).thenReturn(14).thenReturn(19);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(25).thenReturn(25).thenReturn(89);

        assertEquals("_2525894|25\n" +
                " 25     |------\n" +
                " --     |101035\n" +
                "  _25\n" +
                "   25\n" +
                "   --\n" +
                "    _89\n" +
                "     75\n" +
                "     --\n" +
                "    _144\n" +
                "     125\n" +
                "     ---\n" +
                "      19"       , lineFormatter.divide(2525894, 25));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenDividendStartsWithTheSameFiguresAsDivisorTwiceAndDividendIsNegative() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(-101035);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(-25).thenReturn(-25).thenReturn(-25).thenReturn(-75).thenReturn(-125);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(4);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(-25).thenReturn(-25).thenReturn(-25).thenReturn(-89).thenReturn(-144).thenReturn(-19);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(0).thenReturn(-14).thenReturn(-19);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(-25).thenReturn(-25).thenReturn(-89);

        assertEquals("_-2525894|25\n" +
                " -25     |-------\n" +
                "  --     |-101035\n" +
                "  _-25\n" +
                "   -25\n" +
                "    --\n" +
                "    _-89\n" +
                "     -75\n" +
                "      --\n" +
                "    _-144\n" +
                "     -125\n" +
                "      ---\n" +
                "      -19"       , lineFormatter.divide(-2525894, 25));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenDividendStartsWithTheSameFiguresAsDivisorTwiceAndDividerIsNegative() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(-101035);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(25).thenReturn(25).thenReturn(25).thenReturn(75).thenReturn(125);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(4);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(25).thenReturn(25).thenReturn(25).thenReturn(89).thenReturn(144).thenReturn(19);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(0).thenReturn(14).thenReturn(19);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(25).thenReturn(25).thenReturn(89);

        assertEquals("_2525894|-25\n" +
                " 25     |-------\n" +
                " --     |-101035\n" +
                "  _25\n" +
                "   25\n" +
                "   --\n" +
                "    _89\n" +
                "     75\n" +
                "     --\n" +
                "    _144\n" +
                "     125\n" +
                "     ---\n" +
                "      19"       , lineFormatter.divide(2525894, -25));

    }

    @Test
    void test_divide_ShouldReturnIntegersDivisionScheme_WhenDividendStartsWithTheSameFiguresAsDivisorTwiceAndBothAreNegative() {
        when(mockedDivider.returnDivisionResult(anyInt(), anyInt())).thenReturn(101035);
        when(mockedDivider.calculateSubtrahend(anyInt(), anyInt(), anyInt())).thenReturn(25).thenReturn(25).thenReturn(25).thenReturn(75).thenReturn(125);
        when(mockedDivider.findNumberOfIterations(anyInt())).thenReturn(4);
        when(mockedDivider.defineNewDividendPart(anyInt(),anyInt())).thenReturn(25).thenReturn(25).thenReturn(25).thenReturn(89).thenReturn(144).thenReturn(19);
        when(mockedDivider.calculateRemainder(anyInt(),anyInt())).thenReturn(0).thenReturn(14).thenReturn(19);
        when(mockedDivider.calculateNewDividendPart(anyInt(),anyInt())).thenReturn(25).thenReturn(25).thenReturn(89);

        assertEquals("_2525894|25\n" +
                " 25     |------\n" +
                " --     |101035\n" +
                "  _25\n" +
                "   25\n" +
                "   --\n" +
                "    _89\n" +
                "     75\n" +
                "     --\n" +
                "    _144\n" +
                "     125\n" +
                "     ---\n" +
                "      19"       , lineFormatter.divide(-2525894, -25));

    }
}
