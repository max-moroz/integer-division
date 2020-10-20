package ua.com.foxminded.integerdivision.math;


public class Divider {

    public int returnDivisionResult(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException();
        }

        int result = 0;

        if (dividend >= 0 && divisor > 0) {
            while (dividend >= divisor) {
                dividend -= divisor;
                result++;
            }
        } else if (dividend < 0 && divisor < 0) {
            while (dividend <= divisor) {
                dividend -= divisor;
                result++;
            }
        } else if (dividend < 0) {
            while (dividend <= -divisor) {
                dividend += divisor;
                result--;
            }
        } else {
            while (-dividend <= divisor) {
                dividend += divisor;
                result--;
            }
        }
        return result;
    }

    public int calculateSubtrahend(int quotient, int divisor, int figureIndex) {
        if (quotient == 0) {
            return 0;
        }

        String[] figures = Integer.toString(quotient).split("");
        int counter = findNumberOfIterations(quotient);
        int[] figuresExceedingZero = new int[counter];
        int index = 0;

        for (String figure : figures) {

            if (figure.compareTo("0") != 0 && !figure.contains("-")) {

                figuresExceedingZero[index] = Integer.parseInt(figure);
                index++;
            }
        }

        int subtrahend = figuresExceedingZero[figureIndex] * divisor;

        if (quotient < 0) {
            subtrahend = Math.negateExact(subtrahend);
        }

        return subtrahend;
    }

    public int findNumberOfIterations(int quotient) {
        String[] figures = Integer.toString(quotient).split("");
        int counter = 0;

        for (String fig : figures) {

            if (fig.compareTo("0") != 0 && !fig.contains("-")) {
                counter++;
            }
        }

        if (quotient < 10 && quotient > -10) {
            counter = 1;
        }

        return counter;
    }

    public int defineNewDividendPart(int dividend, int divisor) {
        if ((dividend < divisor && dividend > 0) || (dividend > divisor && dividend < 0)) {
            return dividend;
        }

        String[] digitsOfDividend = String.valueOf(dividend).split("");
        StringBuilder newDividend = new StringBuilder();

        int valueOfDividend = 0;

        for (String digits : digitsOfDividend) {
            if (!digits.contains("-")) {
                newDividend.append(digits);

                valueOfDividend = Integer.parseInt(newDividend.toString());

                if (valueOfDividend >= Math.abs(divisor)) {
                    return dividend > 0 ? valueOfDividend : Math.negateExact(valueOfDividend);
                }
            }
        }

        return dividend > 0 ? valueOfDividend : Math.negateExact(valueOfDividend);
    }

    public int rewriteDividend(int dividend, int subtrahend, int newDividendPart) {
        int zeroesLength = calculateDigit(dividend) - calculateDigit(subtrahend);
        int multi = 10;

        if (calculateDigit(newDividendPart) > calculateDigit(subtrahend)) {
            zeroesLength--;
        }

        multi = (int) Math.pow(multi, zeroesLength);

        int result = 0;

        for (int i = 0; i < multi; i++) {
            result += subtrahend;
        }

        return dividend - result;
    }

    private int calculateDigit(int i) {
        return i == 0 ? 1 : (int) Math.log10(Math.abs(i)) + 1;
    }

    public int calculateRemainder(int newDividendPart, int divisor) {
        return newDividendPart % divisor;
    }

    public int calculateNewDividendPart(int subtrahend, int remainder) {
        return subtrahend + remainder;
    }
}
