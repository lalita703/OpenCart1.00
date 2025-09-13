package utilitys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utility
{
	private String path;       // Excel file path
    private FileInputStream fis;
    private FileOutputStream fos;
    private Workbook workbook;
    private Sheet sheet;
    private Row row;
    private Cell cell;

    // Constructor
    public Excel_Utility(String path) {
        this.path = path;
    }

    // Get Row Count
    public int getRowCount(String sheetName) throws IOException {
        fis = new FileInputStream(path);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);
        int rowCount = (sheet != null) ? sheet.getLastRowNum() : 0;
        workbook.close();
        fis.close();
        return rowCount;
    }

    // Get Cell Count
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);
        row = (sheet != null) ? sheet.getRow(rowNum) : null;
        int cellCount = (row != null) ? row.getLastCellNum() : 0;
        workbook.close();
        fis.close();
        return cellCount;
    }

    // Get Cell Data
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);
        row = (sheet != null) ? sheet.getRow(rowNum) : null;
        cell = (row != null) ? row.getCell(colNum) : null;

        DataFormatter formatter = new DataFormatter();
        String data = (cell != null) ? formatter.formatCellValue(cell) : "";
        workbook.close();
        fis.close();
        return data;
    }

    // Set Cell Data (with checks for file/sheet/row/cell)
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File file = new File(path);

        if (!file.exists()) {
            workbook = new XSSFWorkbook(); // new workbook if file doesn't exist
            sheet = workbook.createSheet(sheetName);
        } else {
            fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);
            fis.close();
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }
        }

        row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(data);

        fos = new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fos.close();
    }

    // Fill Green Color in Cell
    public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
        fis = new FileInputStream(path);
        workbook = WorkbookFactory.create(fis);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        // Create Cell Style with Green Background
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fos = new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
    }
}
