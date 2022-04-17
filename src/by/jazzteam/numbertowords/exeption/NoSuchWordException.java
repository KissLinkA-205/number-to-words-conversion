package by.jazzteam.numbertowords.exeption;

/**
 * {@code NoSuchWordException} is generated in case entity doesn't found in handbook.
 *
 * @author Anzhalika Dziarkach
 * @version 1.0
 * @see RuntimeException
 */
public class NoSuchWordException extends RuntimeException {

    public NoSuchWordException() {
    }

    public NoSuchWordException(String messageCode) {
        super(messageCode);
    }

    public NoSuchWordException(String messageCode, Throwable cause) {
        super(messageCode, cause);
    }

    public NoSuchWordException(Throwable cause) {
        super(cause);
    }
}
