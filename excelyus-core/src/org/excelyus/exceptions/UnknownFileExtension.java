package org.excelyus.exceptions;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 11:43<br/>
 * To change this template use File | Settings | File Templates.
 */
public class UnknownFileExtension extends Exception {

    public UnknownFileExtension(String message) {
        super(message);
    }
}
