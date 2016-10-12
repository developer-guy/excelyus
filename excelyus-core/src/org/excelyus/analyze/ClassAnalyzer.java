package org.excelyus.analyze;

import org.apache.log4j.Logger;
import org.excelyus.annotations.Excel;
import org.excelyus.annotations.ExcelColumn;
import org.excelyus.exceptions.NoExcelFileAnnotationFoundOnClassException;
import org.excelyus.exceptions.NullValueFoundOnNotNullableField;
import org.excelyus.logger.ExcelyusLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 14:00<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ClassAnalyzer {

    protected static Logger LOG = ExcelyusLogger.buildLogger(ClassAnalyzer.class);

    public static void analyzeClassBeforeAdd(Class<?> clasz) throws NoExcelFileAnnotationFoundOnClassException {
        Annotation annotationsByType = clasz.getAnnotation(Excel.class);
        if(annotationsByType == null){
            throw new NoExcelFileAnnotationFoundOnClassException("There is no suitable excel file class.");
        }
    }

    public static void analyzeClass(Object obj) throws NullValueFoundOnNotNullableField {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        try {
            for(Field field : declaredFields){
                field.setAccessible(true);
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                Object o = field.get(obj);
                if(o == null && annotation.nullable() == false){
                    throw new NullValueFoundOnNotNullableField("The value of the field '"+field.getName()+"' found null,but in class design you sign it nullable = false .");
                }
            }
        } catch (IllegalAccessException e) {
            LOG.error(e.toString());
        }
    }
}
