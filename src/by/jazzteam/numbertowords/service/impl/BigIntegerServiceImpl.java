package by.jazzteam.numbertowords.service.impl;

import by.jazzteam.numbertowords.exeption.NoSuchWordException;
import by.jazzteam.numbertowords.service.BigIntegerService;
import by.jazzteam.numbertowords.service.NumberHandbookService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Class {@code BigIntegerServiceImp} is implementation of interface {@link BigIntegerService}
 * and intended to work with {@link BigInteger} objects.
 *
 * @author Anzhalika Dziarkach
 * @version 1.0
 */
public class BigIntegerServiceImpl implements BigIntegerService {
    private static final NumberHandbookService handbookService = new HandbookNumberServiceImpl();
    private static final String SEPARATOR = " ";
    private static final int STANDARD_FORM_POSITION = 0;

    @Override
    public String convertToWords(BigInteger number) throws IOException {
        if (number.compareTo(BigInteger.ZERO) == 0) {
            Optional<String> zero = handbookService.getWordFromNumberHandbookByKey("0", STANDARD_FORM_POSITION);
            if (zero.isPresent()) {
                return zero.get();
            } else {
                throw new NoSuchWordException("Number " + number + " not found in handbook");
            }
        }

        StringBuilder result = new StringBuilder();

        if (number.compareTo(BigInteger.ZERO) < 0) {
            Optional<String> sign = handbookService.getWordFromUnitHandbookByKey("-", STANDARD_FORM_POSITION);
            if (sign.isPresent()) {
                result.append(sign.get()).append(SEPARATOR);
            } else {
                throw new NoSuchWordException("Unit - not found in handbook");
            }
            number = number.multiply(BigInteger.valueOf(-1));
        }

        List<BigInteger> segments = convertNumberToSegments(number);

        for (int i = 0; i < segments.size(); i++) {
            BigInteger segment = segments.get(i);
            if (segment.compareTo(BigInteger.ZERO) == 0) {
                continue;
            }
            int degree = segments.size() - i - 1;
            String convertedSegment = convertSegmentToWords(segment, degree);
            result.append(convertedSegment);
        }

        return result.substring(0, result.length() - 1);
    }

    private String convertSegmentToWords(BigInteger segment, int degree) throws IOException {
        StringBuilder result = new StringBuilder();

        if (segment.compareTo(BigInteger.valueOf(99)) > 0) {
            BigInteger numberToSearch = BigInteger.valueOf((segment.longValue() / 100) * 100);
            Optional<String> word = handbookService.getWordFromNumberHandbookByKey(numberToSearch.toString(), STANDARD_FORM_POSITION);
            if (word.isPresent()) {
                result.append(word.get()).append(SEPARATOR);
            } else {
                throw new NoSuchWordException("Number " + numberToSearch + " not found in handbook");
            }
            segment = segment.mod(BigInteger.valueOf(100));
        }

        if (segment.compareTo(BigInteger.valueOf(20)) > 0) {
            BigInteger numberToSearch = BigInteger.valueOf(segment.longValue() - segment.longValue() % 10);
            Optional<String> word = handbookService.getWordFromNumberHandbookByKey(numberToSearch.toString(), STANDARD_FORM_POSITION);
            if (word.isPresent()) {
                result.append(word.get()).append(SEPARATOR);
            } else {
                throw new NoSuchWordException("Number " + numberToSearch + " not found in handbook");
            }

            segment = segment.mod(BigInteger.valueOf(10));
            numberToSearch = BigInteger.valueOf(segment.longValue() % 10);
            if (!segment.equals(BigInteger.ZERO)) {
                word = handbookService.getWordFromNumberHandbookByKey(numberToSearch.toString(), selectNumberForm(degree, segment.longValue()));
                if (word.isPresent()) {
                    result.append(word.get()).append(SEPARATOR);
                } else {
                    throw new NoSuchWordException("Number " + numberToSearch + " not found in handbook");
                }
            }
        } else if (segment.compareTo(BigInteger.ZERO) != 0) {
            Optional<String> word = handbookService.getWordFromNumberHandbookByKey(segment.toString(), selectNumberForm(degree, segment.longValue()));
            if (word.isPresent()) {
                result.append(word.get()).append(SEPARATOR);
            } else {
                throw new NoSuchWordException("Number " + segment + " not found in handbook");
            }
        }

        result.append(getFormNameUnit(degree, segment.longValue()));
        return result.toString();
    }

    private List<BigInteger> convertNumberToSegments(BigInteger number) {
        List<BigInteger> segments = new ArrayList();
        while (number.compareTo(BigInteger.valueOf(999)) > 0) {
            BigInteger segment = number.divide(BigInteger.valueOf(1000));
            segments.add(number.subtract(segment.multiply(BigInteger.valueOf(1000))));
            number = segment;
        }
        segments.add(number);
        Collections.reverse(segments);

        return segments;
    }

    private String getFormNameUnit(int degree, long number) throws IOException {
        if (String.valueOf(number).equals("000")) return "";
        if (degree >= 1) {
            Optional<String> unit = handbookService.getWordFromUnitHandbookByKey(String.valueOf(degree), selectUnitForm(number));
            if (unit.isPresent()) {
                return unit.get() + SEPARATOR;
            }
        }
        return "";
    }

    private int selectUnitForm(long number) {
        number = Math.abs(number) % 100;
        if (number > 10 && number < 20) return 2;
        if (number % 10 > 1 && number % 10 < 5) return 1;
        if (number % 10 == 1) return 0;
        return 2;
    }

    private int selectNumberForm(int degree, long number) {
        number = Math.abs(number) % 100;
        if (number > 2 || number < 1 || degree != 1) return 0;
        return 1;
    }
}
