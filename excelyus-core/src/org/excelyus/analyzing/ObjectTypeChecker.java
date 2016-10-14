package org.excelyus.analyzing;

import org.excelyus.exceptions.DifferentObjectTypeThenExpectedException;
import org.excelyus.helper.MessagesHelper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 13:34<br/>
 * To change this template use File | Settings | File Templates.
 */
public final class ObjectTypeChecker {

    private ObjectTypeChecker(){
        //private Constructor
    }

    public static void checkObjectsType(Class<?> clasz, List<?> objects) throws DifferentObjectTypeThenExpectedException {
        if(objects != null && objects.size() != 0) {
            for (Object obj : objects) {
                if (!clasz.isInstance(obj)) {
                    String claszName = clasz.getName();
                    String objClaszName = obj.getClass().getName();
                    throw new DifferentObjectTypeThenExpectedException(MessagesHelper.get("DifferentObjectTypeThenExpectedException.message", claszName,objClaszName).toString());
                }
            }
        }
    }
}
