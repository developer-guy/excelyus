package org.excelyus.logger;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 13:20<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ExcelyusLogger{

    public static Logger buildLogger(String name){
        return ExcelyusLoggerFactory.getLogger(name);
    }

    public static Logger buildLogger(Class<?> clasz){
        return ExcelyusLoggerFactory.getLogger(clasz);
    }
}
