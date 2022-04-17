package by.jazzteam.numbertowords.service;

import java.io.IOException;
import java.util.Optional;

/**
 * Interface {@code NumberHandbookService} describes operations for working with number handbook.
 *
 * @author Anzhalika Dziarkach
 * @version 1.0
 */
public interface NumberHandbookService {

    /**
     * Method for getting word format of number.
     *
     * @param key number converted to string
     * @param formPosition position of the number form in the handbook
     * @return Optional of found word
     */
    Optional<String> getWordFromNumberHandbookByKey(String key, int formPosition) throws IOException;

    /**
     * Method for getting word format of unit.
     *
     * @param key unit degree converted to string
     * @param formPosition position of the unit form in the handbook
     * @return Optional of found word
     */
    Optional<String> getWordFromUnitHandbookByKey(String key, int formPosition) throws IOException;
}
