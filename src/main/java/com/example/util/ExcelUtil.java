package com.example.util;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {
    private final Workbook workbook;

    public ExcelUtil(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(new File(filePath));
        this.workbook = WorkbookFactory.create(file);
        file.close();
    }

    public String getData(int sheetIndex, int rowIndex, int cellIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(cellIndex);
        return cell.getStringCellValue();
    }

    public int getTotalRowCount(int sheetIndex) {
        return workbook.getSheetAt(sheetIndex).getPhysicalNumberOfRows();
    }

    public int getTotalCellCount(int sheetIndex, int rowIndex) {
        return workbook.getSheetAt(sheetIndex).getRow(rowIndex).getPhysicalNumberOfCells();
    }
}
