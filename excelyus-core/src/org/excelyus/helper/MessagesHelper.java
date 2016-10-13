package org.excelyus.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.excelyus.analyzing.ClassAnnotationProcessor;
import org.excelyus.logging.ExcelyusLogger;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 08:51<br/>
 * To change this template use File | Settings | File Templates.
 */
public final class MessagesHelper {

    private static Properties properties =  new Properties();
    private static final Logger LOG = ExcelyusLogger.buildLogger(ClassAnnotationProcessor.class);

    static {
        initialize();
    }

    private MessagesHelper() {
        //private constructor
    }

    public static Object get(String key) {
        if(StringUtils.equals("",key) || key == null){
            return "";
        }
        if(!properties.containsKey(key)){
            return "";
        }
        Object obj =  properties.get(key);
        if(obj == null){
            return "";
        }
        return obj;
    }

    public static Object get(String key,Object... params) {
        if(StringUtils.equals("",key) || key == null){
            return "";
        }
        if(!properties.containsKey(key)){
            return "";
        }
        Object obj = properties.get(key);
        if(obj == null){
            return "";
        }
        return MessageFormat.format(obj.toString(),params);
    }

    private static void initialize() {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("messages.properties");
            if(resourceAsStream != null){
                properties.load(resourceAsStream);
            }
        } catch (IOException e) {
            LOG.error(e.toString());
        }finally {
            try {
                if(resourceAsStream != null){
                    resourceAsStream.close();
                }
            } catch (IOException e) {
                LOG.error(e.toString());
            }
        }
    }
}
