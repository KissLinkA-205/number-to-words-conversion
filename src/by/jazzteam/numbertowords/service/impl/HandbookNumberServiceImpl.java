package by.jazzteam.numbertowords.service.impl;

import by.jazzteam.numbertowords.entity.NumberHandbook;
import by.jazzteam.numbertowords.entity.UnitHandbook;
import by.jazzteam.numbertowords.service.NumberHandbookService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Class {@code HandbookNumberServiceImpl} is implementation of interface {@link NumberHandbookService}
 * and intended to work with {@link NumberHandbook} and {@link UnitHandbook} objects.
 *
 * @author Anzhalika Dziarkach
 * @version 1.0
 */
public class HandbookNumberServiceImpl implements NumberHandbookService {
    private static final String SEPARATOR = " ";
    private static final NumberHandbook numberHandbook = new NumberHandbook();
    private static final UnitHandbook unitHandbook = new UnitHandbook();

    @Override
    public Optional<String> getWordFromNumberHandbookByKey(String key, int formPosition) throws IOException {
        return readWordFromHandbook(key, numberHandbook.getFilePath(), formPosition);
    }

    @Override
    public Optional<String> getWordFromUnitHandbookByKey(String key, int formPosition) throws IOException {
        return readWordFromHandbook(key, unitHandbook.getFilePath(), formPosition);
    }

    private Optional<String> readWordFromHandbook(String key, String filePath, int formPosition) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            BufferedReader buffReader = new BufferedReader(reader);
            String line;

            do {
                line = buffReader.readLine();

                if (line != null) {
                    String[] args = line.split(SEPARATOR);
                    if (Objects.equals(args[0], key)) {
                        try {
                            return Optional.of(args[formPosition + 1]);
                        } catch (Exception ex) {
                            return Optional.empty();
                        }

                    }
                }
            } while (line != null);

        } catch (IOException ex) {
            throw new IOException(ex);
        }

        return Optional.empty();
    }
}
