package org.excelyus.creator;

import org.apache.log4j.Logger;
import org.excelyus.analyze.ClassAnalyzer;
import org.excelyus.exceptions.NoExcelFileAnnotationFoundOnClassException;
import org.excelyus.exceptions.NullValueFoundOnNotNullableField;
import org.excelyus.exceptions.ObjectIsNullException;
import org.excelyus.logger.ExcelyusLogger;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 13:56<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ExcelFileCreator {

    protected static Logger LOG = ExcelyusLogger.buildLogger(ClassAnalyzer.class);

    public static Object addExcelClass(Class<?> clasz) {
        Object o = null;
        try {
            ClassAnalyzer.analyzeClassBeforeAdd(clasz);
            o = clasz.newInstance();
        } catch (InstantiationException e) {
            LOG.error(e.toString() + ". Your object must be contain default constructor.");
        } catch (IllegalAccessException e) {
            LOG.error(e.toString() + ". Your object must be contain default public constructor,found private");
        }catch (NoExcelFileAnnotationFoundOnClassException e){
            LOG.error(e.toString());
        }
        return o;
    }

    public static void createExcelFile(Object obj) throws NullValueFoundOnNotNullableField,ObjectIsNullException {
        if(obj == null){
            throw new ObjectIsNullException("The object is found null.");
        }
        ClassAnalyzer.analyzeClass(obj);
    }


}
