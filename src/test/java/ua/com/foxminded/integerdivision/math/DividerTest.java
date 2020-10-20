package ua.com.foxminded.integerdivision.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DividerTest {

    Divider divider = new Divider();

    @Test
    void test_returnDivisionResult_ShouldThrowException_WhenInputZeroDivider() {
        assertThrows(IllegalArgumentException.class, () -> divider.returnDivisionResult(5, 0));
    }

    @Test
    void test_returnDivisionResult_ShouldReturnZero_WhenDividendIsZero() {
        assertEquals(0, divider.returnDivisionResult(0, 3));
    }

    @Test
    void test_returnDivisionResult_ShouldReturnZero_WhenDividendIsLessThenDivisor() {
        assertEquals(0, divider.returnDivisionResult(2, 8));
    }

    @Test
    void test_returnDivisionResult_ShouldReturnOne_WhenDividendEqualsToDivisor() {
        assertEquals(1, divider.returnDivisionResult(27, 27));
    }

    @Test
    void test_returnDivisionResult_ShouldReturnFive_WhenDividendlsFifthAsMuchAsDivisor() {
        assertEquals(5, divider.returnDivisionResult(100, 20));
    }

    @Test
    void test_returnDivisionResult_ShouldReturnFour_WhenDividendlsABitLessThenFifthAsMuchAsDivisor() {
        assertEquals(4, divider.returnDivisionResult(99, 20));
    }

    @Test
    void test_calculateSubtrahend_ShouldReturnZero_WhenQuotientIsZero(){
        assertAll(
                () -> assertEquals(0, divider.calculateSubtrahend(0, 0, 0)),
                () -> assertEquals(0, divider.calculateSubtrahend(0, 5, 2)),
                () -> assertEquals(0, divider.calculateSubtrahend(0, 5, 5)),
                () -> assertEquals(0, divider.calculateSubtrahend(0, 2, 5))
        );
    }

    @Test
    void test_calculateSubtrahend_ShouldReturn105_WhenQuotientIs3559DivisorIs35FigureIndex0(){
        assertEquals(105, divider.calculateSubtrahend(3559, 35, 0));
    }

    @Test
    void test_calculateSubtrahend_ShouldReturn315_WhenQuotientIs3559DivisorIs35FigureIndex3(){
        assertEquals(315, divider.calculateSubtrahend(3559, 35, 3));
    }

    @Test
    void test_calculateSubtrahend_ShouldReturnMinus315_WhenQuotientIsMinus3559DivisorIs35FigureIndex3(){
        assertEquals(-315, divider.calculateSubtrahend(-3559, 35, 3));
    }

    @Test
    void test_findNumberOfIterations_ShouldReturn1_WhenQuotientIsLessThen10AndMoreThenMinus10() {
        assertAll(
                () -> assertEquals(1, divider.findNumberOfIterations(-9)),
                () -> assertEquals(1, divider.findNumberOfIterations(0)),
                () -> assertEquals(1, divider.findNumberOfIterations(9))
        );
    }

    @Test
    void test_findNumberOfIterations_ShouldReturn2_WhenQuotientConsistsOfFourFiguresAndTwoOfThemAreZero() {
        assertAll(
                () -> assertEquals(2, divider.findNumberOfIterations(-3050)),
                () -> assertEquals(2, divider.findNumberOfIterations(3050))
        );
    }

    @Test
    void test_findNumberOfIterations_ShouldReturn4_WhenQuotientConsistsOfFourFiguresAndNoneOfThemAreZero() {
        assertAll(
                () -> assertEquals(4, divider.findNumberOfIterations(-3455)),
                () -> assertEquals(4, divider.findNumberOfIterations(3455))
        );
    }

    @Test
    void test_defineNewDividendPart_ShouldReturnDividend_WhenDividendIsLessThenDivisorAndDividendMoreThanZero() {
        assertAll(
                () -> assertEquals(5, divider.defineNewDividendPart(5, 10)),
                () -> assertEquals(163, divider.defineNewDividendPart(163, 164))
        );
    }

    @Test
    void test_defineNewDividendPart_ShouldReturnDividend_WhenDividendIsMoreThenDivisorAndDividendLessThanZero() {
        assertAll(
                () -> assertEquals(-5, divider.defineNewDividendPart(-5, -10)),
                () -> assertEquals(-163, divider.defineNewDividendPart(-163, -164))
        );
    }

    @Test
    void test_defineNewDividendPart_ShouldReturnNewDividendPart_WhenDividendIsMoreThenDivisor() {
        assertEquals(123, divider.defineNewDividendPart(123456, 25));
    }

    void test_defineNewDividendPart_ShouldReturnNegativeNewDividendPart_WhenDividendIsNegativeAndAbsDividendIsMoreThenDivisor() {
        assertEquals(-123, divider.defineNewDividendPart(-123456, 25));
    }

    @Test
    void test_rewriteDividend_ShouldReturnNewDividend() {
        assertAll(
                () -> assertEquals(19578, divider.rewriteDividend(124578, 105, 124)),
                () -> assertEquals(-19578, divider.rewriteDividend(-124578, -105, -124))
        );
    }

    @Test
    void test_calculateRemainder_ShouldReturnRemainder_WhenNewDividendPartDividedByDivisor() {
        assertAll(
                () -> assertEquals(20, divider.calculateRemainder(195, 35)),
                () -> assertEquals(32, divider.calculateRemainder(207, 35))
        );
    }

    @Test
    void test_calculateNewDividendPart_ShouldReturnNewDividendPart_WhenInputSubtrahendAndRemainder() {
        assertAll(
                () -> assertEquals(195, divider.calculateNewDividendPart(175, 20)),
                () -> assertEquals(328, divider.calculateNewDividendPart(315, 13))
        );
    }
}
