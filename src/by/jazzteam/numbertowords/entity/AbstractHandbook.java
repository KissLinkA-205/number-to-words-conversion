package by.jazzteam.numbertowords.entity;

/**
 * Abstract class {@code AbstractHandbook} represents handbook entity.
 *
 * @author Anzhalika Dziarkach
 * @version 1.0
 */
public abstract class AbstractHandbook {
    private final String filePath;

    protected AbstractHandbook(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
