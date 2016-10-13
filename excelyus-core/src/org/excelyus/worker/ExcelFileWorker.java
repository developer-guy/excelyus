package org.excelyus.worker;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.excelyus.annotations.Column;
import org.excelyus.annotations.Excel;
import org.excelyus.exceptions.UnknownFileExtension;
import org.excelyus.helper.MessagesHelper;
import org.excelyus.option.HeaderOptions;
import org.excelyus.util.StringProcessor;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 11:33<br/>
 * To change this template use File | Settings | File Templates.
 */
public final class ExcelFileWorker {
    private static final String EXCEL2003_FILE_EXTENSION = ".xls";
    private static final String EXCEL2007_FILE_EXTENSION = ".xlsx";

    public byte[] createExcelFile(Class<?> clasz, List<?> objects, HeaderOptions headerOptions) throws UnknownFileExtension, IOException {
        Workbook workbook = getWorkbook(clasz);
        workbook = createExcelHeader(clasz,workbook,headerOptions);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        byte[] byteArr = bos.toByteArray();
        return byteArr;
    }

    private Workbook createExcelHeader(Class<?> clasz, Workbook workbook,HeaderOptions headerOptions) {
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        CellStyle cellStyleForHeader = createCellStyleForHeader(sheet,headerOptions);
        Field[] declaredFields = clasz.getDeclaredFields();
        for(Field field : declaredFields){
            Column[] annotationsByType = field.getAnnotationsByType(Column.class);
            for(int i = 0 ; i < annotationsByType.length ; i++){
                Cell cell = row.createCell(i);
                cell.setCellStyle(cellStyleForHeader);
                if(StringUtils.equals(annotationsByType[i].columnName(),StringUtils.EMPTY)){
                    cell.setCellValue(field.getName());
                }else{
                    cell.setCellValue(annotationsByType[i].columnName());
                }
            }
        }
        return workbook;
    }

    private CellStyle createCellStyleForHeader(Sheet sheet,HeaderOptions headerOptions) {
        if(headerOptions == null){
            headerOptions = new HeaderOptions();
            headerOptions = createDefaultHeaderOption(headerOptions);
        }
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setColor(headerOptions.getColorIndex());
        font.setBold(headerOptions.isBold());
        font.setFontHeightInPoints(headerOptions.getFontHeightInPoints());
        cellStyle.setFont(font);
        return cellStyle;
    }

    private HeaderOptions createDefaultHeaderOption(HeaderOptions headerOptions) {
        headerOptions.setBold(true);
        headerOptions.setColorIndex(IndexedColors.RED.index);
        headerOptions.setFontHeightInPoints((short)15);
        return headerOptions;
    }

    private Workbook getWorkbook(Class<?> clasz) throws UnknownFileExtension{
        Workbook workbook;
        if(StringUtils.equals(StringProcessor.buildExtension(clasz),EXCEL2003_FILE_EXTENSION)) {
            workbook = new HSSFWorkbook();
        } else if (StringUtils.equals(StringProcessor.buildExtension(clasz),EXCEL2007_FILE_EXTENSION)) {
            workbook = new XSSFWorkbook();
        } else {
            throw new UnknownFileExtension(MessagesHelper.get("UnknownFileExtension.message").toString());
        }
        return workbook;
    }
}
