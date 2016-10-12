package org.excelyus.exceptions;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 14:17<br/>
 * To change this template use File | Settings | File Templates.
 */
public class NoExcelFileAnnotationFoundOnClassException extends Exception {

    public NoExcelFileAnnotationFoundOnClassException(String message) {
        super(message);
    }
}
