package org.excelyus.worker;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.excelyus.analyzing.ClassAnnotationProcessor;
import org.excelyus.annotations.Column;
import org.excelyus.annotations.Excel;
import org.excelyus.exceptions.DifferentObjectTypeThenExpectedException;
import org.excelyus.exceptions.NullValueFoundOnNotNullableField;
import org.excelyus.exceptions.UnknownFileExtension;
import org.excelyus.helper.MessagesHelper;
import org.excelyus.logging.ExcelyusLogger;
import org.excelyus.option.HeaderOptions;
import org.excelyus.util.StringProcessor;

import java.beans.IntrospectionException;
import java.io.ByteArrayOutputStream;
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
public final class ExcelWorker {
    private static final Logger LOG = ExcelyusLogger.buildLogger(ExcelWorker.class);
    private static final String EXCEL2003_FILE_EXTENSION = ".xls";
    private static final String EXCEL2007_FILE_EXTENSION = ".xlsx";

    public byte[] createExcelFile(Class<?> clasz, List<?> objects, HeaderOptions headerOptions)
            throws UnknownFileExtension, NullValueFoundOnNotNullableField, DifferentObjectTypeThenExpectedException {
        ClassAnnotationProcessor.analyzeClaszAnnotations(clasz, objects);
        Workbook workbook = writeToExcel(clasz, objects, headerOptions);
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            bos.close();
        } catch (IOException e) {
            LOG.error(e.toString());
        }
        return bos.toByteArray();
    }

    public Workbook writeToExcel(Class<?> clasz, List<?> objects, HeaderOptions headerOptions) throws UnknownFileExtension {
        Workbook workbook = getWorkbook(clasz);
        try {
            workbook = prepareExcelHeader(clasz, workbook, headerOptions);
            Sheet sheet = workbook.getSheetAt(0);
            for (int t = 1; t <= objects.size(); t++) {
                Row row = sheet.createRow(t);
                Object object = objects.get(t - 1);
                Field[] fields = object.getClass().getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    Cell cell = row.createCell(j);
                    Field field = fields[j];
                    field.setAccessible(true);
                    Object o = field.get(object);
                    if (o != null) {
                        cell.setCellValue(o.toString());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            LOG.error(e.toString());
        }
        return workbook;
    }

    private Workbook prepareExcelHeader(Class<?> clasz, Workbook workbook, HeaderOptions headerOptions) {
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        CellStyle cellStyleForHeader = createCellStyleForHeader(sheet, headerOptions);
        Field[] declaredFields = clasz.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            Column column = field.getAnnotation(Column.class);
            String columnName = column.columnName();
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyleForHeader);
            if (StringUtils.equals(columnName, StringUtils.EMPTY)) {
                cell.setCellValue(field.getName());
            } else {
                cell.setCellValue(columnName);
            }
        }
        return workbook;
    }

    private CellStyle createCellStyleForHeader(Sheet sheet, HeaderOptions headerOptions) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = createFont(sheet, headerOptions);
        if (font != null) {
            cellStyle.setFont(font);
        }
        return cellStyle;
    }

    private Font createFont(Sheet sheet, HeaderOptions headerOptions) {
        Font font = sheet.getWorkbook().createFont();
        if (headerOptions == null) {
            return null;
        }
        font.setColor(headerOptions.getColorIndex());
        font.setBold(headerOptions.isBold());
        font.setFontHeightInPoints(headerOptions.getFontHeightInPoints());
        return font;
    }


    private static Workbook getWorkbook(Class<?> clasz) throws UnknownFileExtension {
        Workbook workbook;
        if (StringUtils.equals(StringProcessor.buildExtension(clasz), EXCEL2003_FILE_EXTENSION)) {
            workbook = new HSSFWorkbook();
        } else if (StringUtils.equals(StringProcessor.buildExtension(clasz), EXCEL2007_FILE_EXTENSION)) {
            workbook = new XSSFWorkbook();
        } else {
            throw new UnknownFileExtension(MessagesHelper.get("UnknownFileExtension.message").toString());
        }
        return workbook;
    }

    public String getExcelFormat(Class<?> clasz) {
        Excel excel = clasz.getAnnotation(Excel.class);
        return excel.format();
    }
}
