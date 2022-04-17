package by.jazzteam.numbertowords.run;

import by.jazzteam.numbertowords.scanner.BigIntegerDataScanner;
import by.jazzteam.numbertowords.service.BigIntegerService;
import by.jazzteam.numbertowords.service.impl.BigIntegerServiceImpl;

import java.io.IOException;
import java.math.BigInteger;

public class Runner {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the number: ");
        BigInteger numberFromInput = BigIntegerDataScanner.readFromConsole();

        BigIntegerService longService = new BigIntegerServiceImpl();
        String result = longService.convertToWords(numberFromInput);
        System.out.println("Result: " + result);
    }
}
