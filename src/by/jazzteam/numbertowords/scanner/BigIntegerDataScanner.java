package by.jazzteam.numbertowords.scanner;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Class {@code BigIntegerDataScanner} needed to read BigInteger from system inputs.
 *
 * @author Anzhalika Dziarkach
 * @version 1.0
 */
public class BigIntegerDataScanner {

    public static BigInteger readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        BigInteger number;

        while (!scanner.hasNextBigInteger()) {
            System.out.print("Invalid input, please enter an integer number: ");
            scanner.next();
        }
        number = scanner.nextBigInteger();

        return number;
    }
}
