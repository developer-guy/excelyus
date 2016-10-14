package org.excelyus.util;

import org.excelyus.worker.ExcelWorker;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 11:39<br/>
 * To change this template use File | Settings | File Templates.
 */
public final class StringProcessor {

    private StringProcessor(){
        //private Constructor
    }

    public static String buildExtension(Class<?> clasz){
        ExcelWorker excelWorker = new ExcelWorker();
        String extension = excelWorker.getExcelFormat(clasz);
        if(!extension.startsWith(".")){
            extension = "." + extension;
        }
        return extension;
    }
}
