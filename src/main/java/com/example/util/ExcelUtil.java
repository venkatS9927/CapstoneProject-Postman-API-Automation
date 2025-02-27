package com.example.util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {
    private final Workbook workbook;

    public ExcelUtil(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        this.workbook = WorkbookFactory.create(file);
        file.close();
    }

    public boolean doesSheetExist(int sheetIndex) {
        try {
            workbook.getSheetAt(sheetIndex);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getData(int sheetIndex, int rowIndex, int cellIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        if (sheet == null) {
            return ""; // Avoid NullPointerException
        }

        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return "";
        }

        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : cell.toString();
    }

    public int getTotalRowCount(int sheetIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        return (sheet != null) ? sheet.getPhysicalNumberOfRows() : 0;
    }

}
