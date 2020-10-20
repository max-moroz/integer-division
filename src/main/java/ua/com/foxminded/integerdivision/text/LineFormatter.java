package ua.com.foxminded.integerdivision.text;

import ua.com.foxminded.integerdivision.math.Divider;

public class LineFormatter {

    private static final String WHITESPACE = " ";
    private static final String DASH = "-";


    Divider divider;

    public LineFormatter(Divider divider) {
        this.divider = divider;
    }

    public String divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException();
        }
        if (dividend < 0 && divisor < 0) {
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);
        }
        return createLine(dividend, divisor);
    }

    private String createLine(int dividend, int divisor) {
        StringBuilder line = new StringBuilder();

        int quotientResult = divider.returnDivisionResult(dividend, divisor);
        int initialDividend = dividend;
        int spaceLength = 1;
        int subtrahend = divider.calculateSubtrahend(quotientResult, divisor, 0);
        int newDividendPart = divider.defineNewDividendPart(dividend, divisor);
        int remainder = newDividendPart % divisor;
        int iterationsNumber = divider.findNumberOfIterations(quotientResult);

        String firstLine = createFirstPartOfTheLine(dividend, divisor);
        StringBuilder secondLine = new StringBuilder();

        int digitsOfSubtrahend = calculateDigit(subtrahend);
        if (remainder > 0 && calculateDigit(newDividendPart) == digitsOfSubtrahend
                && calculateDigit(remainder) == digitsOfSubtrahend) {
            spaceLength--;
        }

        for (int i = 1; i < iterationsNumber; i++) {

            spaceLength = calculateSpaces(subtrahend, remainder, divisor, spaceLength);

            dividend = divider.rewriteDividend(dividend, subtrahend, newDividendPart);
            newDividendPart = divider.defineNewDividendPart(dividend, divisor);
            subtrahend = divider.calculateSubtrahend(quotientResult, divisor, i);
            remainder = divider.calculateRemainder(newDividendPart, divisor);

            secondLine.append(createMainPartOfTheLine(spaceLength, newDividendPart, subtrahend));

            spaceLength++;
        }

        if (calculateDigit(subtrahend) > calculateDigit(remainder)) {
            spaceLength += calculateDigit(subtrahend) - calculateDigit(remainder);
        }

        if (iterationsNumber == 1) {
            spaceLength = calculateDigit(initialDividend);
        }

        dividend = divider.rewriteDividend(dividend, subtrahend, newDividendPart);
        remainder = divider.defineNewDividendPart(dividend, divisor);

        String thirdLine = line.append(repeatSymbol(WHITESPACE, spaceLength)).append(remainder).toString();

        return firstLine + secondLine.toString() + thirdLine;
    }

    private String createFirstPartOfTheLine(int dividend, int divisor) {
        StringBuilder line = new StringBuilder();
        int quotientResult = divider.returnDivisionResult(dividend, divisor);
        int subtrahend = divider.calculateSubtrahend(quotientResult, divisor, 0);
        int newDividendPart = divider.defineNewDividendPart(dividend, divisor);

        line.append("_").append(dividend).append("|").append(divisor).append('\n');

        int spaceLengthBeforeSymbols = calculateSpacesBeforeSymbols(newDividendPart, subtrahend);
        int spaceLengthAfterSymbols = calculateSpacesAfterSymbols(dividend, newDividendPart, subtrahend);

        String spacesToBeInsertedBeforeSymbols = repeatSymbol(WHITESPACE, spaceLengthBeforeSymbols);
        String spacesToBeInsertedAfterSymbols = repeatSymbol(WHITESPACE, spaceLengthAfterSymbols);

        line.append(spacesToBeInsertedBeforeSymbols).append(subtrahend).append(spacesToBeInsertedAfterSymbols)
                .append("|").append(createSeparatorBetweenDivisorAndQuotient(quotientResult, divisor)).append('\n');

        if (subtrahend < 0) {
            spacesToBeInsertedBeforeSymbols = repeatSymbol(WHITESPACE, ++spaceLengthBeforeSymbols);
        }

        String separators = repeatSymbol(DASH, defineTheSeparatorQuantity(subtrahend));

        if ((dividend < 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            separators = repeatSymbol(DASH, defineTheSeparatorQuantity(subtrahend) - 1);
        }

        line.append(spacesToBeInsertedBeforeSymbols).append(separators).append(spacesToBeInsertedAfterSymbols)
                .append("|").append(quotientResult).append('\n');

        return line.toString();
    }

    private String repeatSymbol(String symbol, int length) {
        StringBuilder symbols = new StringBuilder();

        for (int i = 0; i < length; i++) {
            symbols.append(symbol);
        }
        return symbols.toString();
    }

    private int calculateSpacesBeforeSymbols(int newDividendPart, int subtrahend) {
        int spacesQuantity = 1;

        if (calculateDigit(subtrahend) < calculateDigit(newDividendPart)) {
            spacesQuantity++;
        }

        return spacesQuantity;
    }

    private int calculateSpacesAfterSymbols(int dividend, int newDividendPart, int subtrahend) {
        int spacesQuantity = calculateDigit(dividend) - calculateDigit(subtrahend);

        if (calculateDigit(subtrahend) < calculateDigit(newDividendPart)) {
            spacesQuantity--;
        }

        if (calculateDigit(subtrahend) == calculateDigit(dividend)) {
            spacesQuantity = 0;
        }

        return spacesQuantity;
    }

    private String createSeparatorBetweenDivisorAndQuotient(int quotientResult, int divisor) {
        String separatorBetweenDivisorAndQuotient = repeatSymbol(DASH, defineTheSeparatorQuantity(quotientResult));

        if (calculateDigit(quotientResult) == calculateDigit(divisor) && divisor < 0
                || calculateDigit(quotientResult) < calculateDigit(divisor)) {
            separatorBetweenDivisorAndQuotient = repeatSymbol(DASH, defineTheSeparatorQuantity(divisor));
        }

        return separatorBetweenDivisorAndQuotient;
    }

    private String createMainPartOfTheLine(int spaceLength, int dividend, int subtrahend) {
        StringBuilder line = new StringBuilder();

        String spacesToBeInserted = repeatSymbol(WHITESPACE, spaceLength + 1);

        line.append(repeatSymbol(WHITESPACE, spaceLength)).append("_").append(dividend).append('\n');
        line.append(spacesToBeInserted).append(subtrahend).append('\n');

        int separatorsQuantity = defineTheSeparatorQuantity(subtrahend);
        String separators = subtrahend < 0 ? repeatSymbol(DASH, separatorsQuantity - 1)
                : repeatSymbol(DASH, separatorsQuantity);

        if (subtrahend < 0) {
            spacesToBeInserted = repeatSymbol(WHITESPACE, spaceLength + 2);
        }

        line.append(spacesToBeInserted).append(separators).append('\n');

        return line.toString();
    }

    private static int defineTheSeparatorQuantity(int figure) {
        return String.valueOf(figure).toCharArray().length;
    }

    private int calculateSpaces(int subtrahend, int remainder, int divisor, int spaceLength) {
        int newDividendPart = divider.calculateNewDividendPart(subtrahend, remainder);
        int digitsOfRemainder = calculateDigit(remainder);
        int digitsOfSubtrahend = calculateDigit(subtrahend);

        if (remainder != 0 && spaceLength > 0 && digitsOfRemainder == digitsOfSubtrahend
                && digitsOfSubtrahend == calculateDigit(newDividendPart)) {
            spaceLength--;
        }

        if (remainder == 0) {
            spaceLength += calculateDigit(divisor) - 1;
        }

        if (digitsOfSubtrahend - 1 > digitsOfRemainder) {
            spaceLength += digitsOfSubtrahend - 1 - digitsOfRemainder;
        }

        return spaceLength;
    }

    private int calculateDigit(int i) {
        return i == 0 ? 1 : (int) Math.log10(Math.abs(i)) + 1;
    }
}
