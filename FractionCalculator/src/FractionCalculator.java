
import java.util.Scanner;
import java.util.*;


public class FractionCalculator {

    public static void main(String[] args) {

        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers");
        printLineSep();

        Scanner input = new Scanner(System.in);
        String operation = "";
        while(!hasUserQuit(operation)) {
            operation = getOperation(input);
            final Fraction fracA = getFraction(input);
            final Fraction fracB = getFraction(input);
            final Fraction result = getCalculationResult(operation, fracA, fracB);

            System.out.println(fracA.toString() + " " + operation + " " + fracB.toString() + " = " + result.toString());
            printLineSep();
        }
    }

    private static boolean hasUserQuit(String operation){
        return operation.toLowerCase().equals("q");
    }

    private static String getOperation(Scanner input) {

        String chosenOp;
        final Set<String> operation = new HashSet<String>(Arrays.asList("+", "-", "*", "/", "=", "q", "Q"));
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");

        chosenOp = input.nextLine();
        while (!operation.contains(chosenOp)) {
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            chosenOp = input.nextLine();
        }
        return chosenOp;
    }

    private static void printLineSep() {
        StringBuilder sb = new StringBuilder();
        int lineSepSize = 80;

        for (int i = 0; i<=lineSepSize; i++) {
            sb.append("-");
        }

        System.out.println(sb.toString());
    }

    private static boolean validFraction(String input) {

        // reference : https://github.com/msmithnova/DEV277x/blob/master/FractionCalculator/src/FractionCalculator.java
        return input.matches("^-?[0-9]+(\\/[1-9][0-9]*)?$");
    }

    private static Fraction getFraction(Scanner input) {
        String fraction;
        System.out.print("Please enter a fraction (a/b) or integer (a)");

        fraction = input.nextLine();
        while(!validFraction(fraction)) {
            System.out.println("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero");
            fraction = input.nextLine();
        }

        int[] formattedFract = fractionStrToIntArr(fraction);

        return new Fraction(formattedFract[0], formattedFract[1]);
    }

    private static int[] fractionStrToIntArr(String fracStr) {
        int[] fraction = new int[2];

        String[] f = fracStr.split("/");
        fraction[0] = Integer.parseInt(f[0]);
        fraction[1] = Integer.parseInt(f[1]);

        return fraction;
    }

    private static Fraction getCalculationResult(String operation, Fraction fracA, Fraction fracB) {

        Fraction calcResult = new Fraction();

        switch(operation) {

            case "+":
                calcResult = fracA.add(fracB);
                break;
            case "-":
                calcResult = fracA.subtract(fracB);
                break;
            case "/":
                calcResult = fracA.divide(fracB);
                break;
            case "*":
                calcResult = fracA.multiply(fracB);
                break;
        }
        return calcResult;
    }

}
