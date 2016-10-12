package org.excelyus.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 13:27<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ExcelyusLoggerFactory{
    public static Logger getLogger(String s) {
        return Logger.getLogger(s);
    }
    public static Logger getLogger(Class<?> clasz) {
        return Logger.getLogger(clasz);
    }
}
