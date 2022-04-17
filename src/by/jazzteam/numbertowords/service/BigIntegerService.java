package by.jazzteam.numbertowords.service;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Interface {@code BigIntegerService} describes operations for working with BigInteger numbers.
 *
 * @author Anzhalika Dziarkach
 * @version 1.0
 */
public interface BigIntegerService {

    /**
     * Method for converting BigInteger to words format.
     *
     * @param number BigInteger number to convert
     * @return number in words
     */
    String convertToWords(BigInteger number) throws IOException;
}
