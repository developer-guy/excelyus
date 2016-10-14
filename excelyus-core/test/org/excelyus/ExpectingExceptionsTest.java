package org.excelyus;

import org.excelyus.creator.ExcelCreator;
import org.excelyus.exceptions.DifferentObjectTypeThenExpectedException;
import org.excelyus.exceptions.NoExcelFileAnnotationFoundOnClassException;
import org.excelyus.exceptions.NullValueFoundOnNotNullableField;
import org.excelyus.exceptions.UnknownFileExtension;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 14.10.2016<br/>
 * Time: 14:08<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ExpectingExceptionsTest {

    private List<Customer> customers = new ArrayList<>();
    private List<Object> objects = new ArrayList<>();
    @BeforeClass
    public void setupObjects(){
        Customer customer = new Customer();
        customer.setName("Batuhan");
        customer.setSurname("Apaydın");
        customers.add(customer);

        objects.add(customer);
        objects.add("String");
        objects.add(1.2d);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void ifClaszObjectGivenNullTest() throws NoExcelFileAnnotationFoundOnClassException,
                                                    DifferentObjectTypeThenExpectedException,
                                                    NullValueFoundOnNotNullableField,
                                                    UnknownFileExtension {
        ExcelCreator excelCreator = new ExcelCreator(null,null);
        excelCreator.createExcelFile(customers);
    }

    @Test(expectedExceptions = NoExcelFileAnnotationFoundOnClassException.class)
    public void ifObjectDoesntHaveExcelAnnotationTest() throws NoExcelFileAnnotationFoundOnClassException,
                                                               DifferentObjectTypeThenExpectedException,
                                                               NullValueFoundOnNotNullableField,
                                                               UnknownFileExtension {
        ExcelCreator excelCreator = new ExcelCreator(Object.class,null);
        excelCreator.createExcelFile(null);
    }


    @Test(expectedExceptions = DifferentObjectTypeThenExpectedException.class)
    public void ifListHasDifferentTypesTest() throws NoExcelFileAnnotationFoundOnClassException,
                                                     DifferentObjectTypeThenExpectedException,
                                                     NullValueFoundOnNotNullableField,
                                                     UnknownFileExtension {
        ExcelCreator excelCreator = new ExcelCreator(Customer.class,null);
        excelCreator.createExcelFile(objects);
    }

}
