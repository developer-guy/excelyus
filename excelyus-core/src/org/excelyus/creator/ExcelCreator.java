package org.excelyus.creator;

import org.apache.log4j.Logger;
import org.excelyus.analyzing.ClassAnnotationProcessor;
import org.excelyus.analyzing.ClassTypeChecker;
import org.excelyus.exceptions.*;
import org.excelyus.logging.ExcelyusLogger;
import org.excelyus.option.HeaderOptions;
import org.excelyus.worker.ExcelFileWorker;

import java.io.IOException;
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
    private Class<?> clasz = null;

    public ExcelCreator(Class<?> clasz) throws NoExcelFileAnnotationFoundOnClassException {
        ClassAnnotationProcessor.analyzeClaszAnnotationBeforeInitialize(clasz);
        this.clasz = clasz;
    }

    public byte[] createExcelFile(List<?> objects, HeaderOptions headerOptions) throws NullValueFoundOnNotNullableField, DifferentObjectTypeThenExpectedException, UnknownFileExtension, IOException {
        ExcelFileWorker excelFileWorker = new ExcelFileWorker();
        if(objects == null || objects.size() == 0){
            LOG.info("Objects found null or size equal zero.");
        }
        ClassTypeChecker.checkObjectsType(clasz, objects);
        ClassAnnotationProcessor.analyzeClaszAnnotations(objects);
       return excelFileWorker.createExcelFile(clasz,objects,headerOptions);
    }
}
