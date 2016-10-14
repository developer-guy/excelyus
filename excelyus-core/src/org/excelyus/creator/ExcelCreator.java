package org.excelyus.creator;

import org.apache.log4j.Logger;
import org.excelyus.analyzing.ClassAnnotationProcessor;
import org.excelyus.exceptions.DifferentObjectTypeThenExpectedException;
import org.excelyus.exceptions.NoExcelFileAnnotationFoundOnClassException;
import org.excelyus.exceptions.NullValueFoundOnNotNullableField;
import org.excelyus.exceptions.UnknownFileExtension;
import org.excelyus.logging.ExcelyusLogger;
import org.excelyus.option.HeaderOptions;
import org.excelyus.worker.ExcelWorker;

import java.beans.IntrospectionException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 13:56<br/>
 * To change this template use File | Settings | File Templates.
 */
public final class ExcelCreator {
    private static final Logger LOG = ExcelyusLogger.buildLogger(ClassAnnotationProcessor.class);
    private Class<?> clasz;
    private HeaderOptions headerOptions;

    public ExcelCreator(Class<?> clasz,HeaderOptions headerOptions) throws NoExcelFileAnnotationFoundOnClassException {
        ClassAnnotationProcessor.analyzeClaszAnnotationBeforeInitialize(clasz);
        this.clasz = clasz;
        this.headerOptions = headerOptions;
    }

    public byte[] createExcelFile(List<?> objects) throws NullValueFoundOnNotNullableField, DifferentObjectTypeThenExpectedException, UnknownFileExtension {
        ExcelWorker excelFileWorker = new ExcelWorker();
        if(objects == null || objects.size() == 0){
            LOG.info("Objects found null or size equal zero.");
            return null;
        }
       return excelFileWorker.createExcelFile(clasz,objects,headerOptions);
    }
}
