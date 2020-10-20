package ua.com.foxminded.integerdivision;

import ua.com.foxminded.integerdivision.container.Context;
import ua.com.foxminded.integerdivision.math.Divider;
import ua.com.foxminded.integerdivision.text.LineFormatter;

public class Main {

    private static LineFormatter lineFormatter;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.printf("%s %n",
                    "It looks like you've made incorrect input. Please input two parameters: dividend and divisor.");
            System.exit(1);
        }

        int dividend = Integer.parseInt(args[0]);
        int divisor = Integer.parseInt(args[1]);

        Context context = new Context();

        Divider divider = context.getObject(Divider.class);
        LineFormatter lineFormatter = new LineFormatter(divider);
        String output = String.format(lineFormatter.divide(dividend, divisor));
        System.out.printf("%s %n", output);
    }

    static void setLineFormatter(LineFormatter newLineFormatter) {
        lineFormatter = newLineFormatter;
    }
}
