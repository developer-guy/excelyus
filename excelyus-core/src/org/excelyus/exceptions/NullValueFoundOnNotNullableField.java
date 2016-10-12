package org.excelyus.exceptions;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 16:21<br/>
 * To change this template use File | Settings | File Templates.
 */
public class NullValueFoundOnNotNullableField extends Exception {

    public NullValueFoundOnNotNullableField(String message) {
        super(message);
    }
}
