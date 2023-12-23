package core.writers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {

    public static void writeExcel(Object[][] data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            int rowNum = 0;
            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream("src/test/resources/WriteFiles/excelMultiData.xlsx")) {
                workbook.write(outputStream);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void writeExcel(String oneValue) {
        String filePath = "src/test/resources/WriteFiles/excelData.xlsx";
        String sheetName = "Sheet1";
        int rowNum = 0;
        int colNum = 0;

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);
            Row row = sheet.createRow(rowNum);
            Cell cell = row.createCell(colNum);
            cell.setCellValue(oneValue);

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                System.out.println("Data written to Excel successfully.");
            } catch (IOException ioException) {
                System.err.println("Error writing to Excel: " + ioException.getMessage());
            }
        } catch (IOException ioException) {
            System.err.println("Error creating Excel workbook: " + ioException.getMessage());
        }
    }

    @Test(enabled = false)
    public void testObject() {
        Object[][] data = {
                {"Name", "Age", "Country"},
                {"Mohammad", 63, "Saudi Arabia"},
                {"Ismail", 33, "Palestine"},
                {"Omar", 27, "Jordan"}
        };

        writeExcel(data);
    }

    @Test(enabled = false)
    public void testOneValue() {
        writeExcel("data");
    }
}
