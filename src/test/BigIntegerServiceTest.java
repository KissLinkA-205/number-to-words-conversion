package test;

import by.jazzteam.numbertowords.service.BigIntegerService;
import by.jazzteam.numbertowords.service.impl.BigIntegerServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class BigIntegerServiceTest {
    private static final BigIntegerService bigIntegerService = new BigIntegerServiceImpl();
    private static final String TEST_DATA_PATH = "src/test/TestData.xls";
    private static final int START_POSITION = 0;

    @Test
    public void testConvertToWords_ShouldReturnNumberInWords() throws IOException {
        InputStream inputStream = new FileInputStream(TEST_DATA_PATH);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        BigInteger inNumber = BigInteger.valueOf(0);
        String inString = null;

        Sheet sheet = workbook.getSheetAt(START_POSITION);
        for (Row row : sheet) {
            for (Cell cell : row) {
                int cellType = cell.getCellType();

                switch (cellType) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print((inNumber = BigInteger.valueOf((long) cell.getNumericCellValue())) + " = ");
                        break;

                    case Cell.CELL_TYPE_STRING:
                        System.out.println((inString = cell.getStringCellValue()));
                        break;

                    default:
                        break;
                }
            }
            assertEquals("Error in number: " + inNumber, inString, bigIntegerService.convertToWords(inNumber));
        }
    }
}
