package core.readers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;

public class ExcelReader {

    public static String readExcel(String filePath, int rowIndex, int cellIndex) {
        try {
            InputStream inputStream = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(cellIndex);

            if (cell != null) {
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING -> {
                        return cell.getStringCellValue();
                    }
                    case NUMERIC -> {
                        return String.valueOf(cell.getNumericCellValue());
                    }
                }
            }

            workbook.close();
            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Test(enabled = false)
    public void test() {
        String filePath = "src/test/resources/WriteFiles/excelMultiData.xlsx";

        System.out.println(readExcel(filePath, 2, 2));
    }
}
