package org.excelyus.annotations;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 12.10.2016<br/>
 * Time: 13:52<br/>
 * To change this template use File | Settings | File Templates.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {
    String fileName();
    String format() default "xls";
}
