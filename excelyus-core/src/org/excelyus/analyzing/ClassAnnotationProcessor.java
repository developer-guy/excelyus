package org.excelyus.analyzing;

import org.apache.log4j.Logger;
import org.excelyus.annotations.Excel;
import org.excelyus.annotations.Column;
import org.excelyus.exceptions.NoExcelFileAnnotationFoundOnClassException;
import org.excelyus.exceptions.NullValueFoundOnNotNullableField;
import org.excelyus.helper.MessagesHelper;
import org.excelyus.logging.ExcelyusLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 14:00<br/>
 * To change this template use File | Settings | File Templates.
 */
public final class ClassAnnotationProcessor {

    private static final Logger LOG = ExcelyusLogger.buildLogger(ClassAnnotationProcessor.class);

    private ClassAnnotationProcessor() {
        //private constructor
    }

    public static void analyzeClaszAnnotationBeforeInitialize(Class<?> clasz) throws NoExcelFileAnnotationFoundOnClassException {
        Annotation annotationsByType = clasz.getAnnotation(Excel.class);
        if (annotationsByType == null) {
            throw new NoExcelFileAnnotationFoundOnClassException(MessagesHelper.get("NoExcelFileAnnotationFoundOnClassException.message", clasz.getName()).toString());
        }
    }

    public static void analyzeClaszAnnotations(List<?> objects) throws NullValueFoundOnNotNullableField {
        if(objects != null && objects.size() != 0) {
            for (Object obj : objects) {
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                try {
                    if (declaredFields.length == 0) {
                       LOG.error(MessagesHelper.get("NoFieldFoundInClassException.message", obj.getClass().getName()).toString());
                    }
                    for (Field field : declaredFields) {
                        field.setAccessible(true);
                        Column annotation = field.getAnnotation(Column.class);
                        Object o = field.get(obj);
                        if (o == null && !annotation.nullable()) {
                            throw new NullValueFoundOnNotNullableField(MessagesHelper.get("NullValueFoundOnNotNullableField.message", field.getName()).toString());
                        }
                    }
                } catch (IllegalAccessException e) {
                    LOG.error(e.toString());
                }
            }
        }
    }

    public static String getExcelFormat(Class<?> clasz){
        Excel excel = clasz.getAnnotation(Excel.class);
        return excel.format();
    }
}
