package test;

import by.jazzteam.numbertowords.service.NumberHandbookService;
import by.jazzteam.numbertowords.service.impl.HandbookNumberServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NumberHandbookServiceTest {
    private static final NumberHandbookService numberHandbookService = new HandbookNumberServiceImpl();

    private static final String CORRECT_NUMBER_KEY = "15";
    private static final String CORRECT_UNIT_KEY = "-";
    private static final String INCORRECT_UNIT_KEY = "+";
    private static final int CORRECT_FORM_POSITION = 0;
    private static final int INCORRECT_FORM_POSITION = 6;

    @Test
    public void testGetWordFromNumberHandbookByKey_ShouldReturnWord_WhenWordIsExist() throws IOException {
        Optional<String> actual = numberHandbookService.getWordFromNumberHandbookByKey(CORRECT_NUMBER_KEY, CORRECT_FORM_POSITION);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testGetWordFromNumberHandbookByKey_ShouldReturnNull_WhenWordIsNotExist() throws IOException {
        Optional<String> actual = numberHandbookService.getWordFromNumberHandbookByKey(CORRECT_NUMBER_KEY, INCORRECT_FORM_POSITION);
        assertFalse(actual.isPresent());
    }

    @Test
    public void testGetWordFromUnitHandbookByKey_ShouldReturnWord_WhenWordIsExist() throws IOException {
        Optional<String> actual = numberHandbookService.getWordFromUnitHandbookByKey(CORRECT_UNIT_KEY, CORRECT_FORM_POSITION);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testGetWordFromUnitHandbookByKey_ShouldReturnNull_WhenWordIsNotExist() throws IOException {
        Optional<String> actual = numberHandbookService.getWordFromUnitHandbookByKey(INCORRECT_UNIT_KEY, CORRECT_FORM_POSITION);
        assertFalse(actual.isPresent());
    }
}
