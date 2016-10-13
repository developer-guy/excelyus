package org.excelyus.logging;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 13:27<br/>
 * To change this template use File | Settings | File Templates.
 */
public final class ExcelyusLoggerFactory{
    public static Logger getLogger(String s) {
        return Logger.getLogger(s);
    }
    public static Logger getLogger(Class<?> clasz) {
        return Logger.getLogger(clasz);
    }
}
