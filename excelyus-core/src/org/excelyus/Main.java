package org.excelyus;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.excelyus.creator.ExcelCreator;
import org.excelyus.exceptions.DifferentObjectTypeThenExpectedException;
import org.excelyus.exceptions.NoExcelFileAnnotationFoundOnClassException;
import org.excelyus.exceptions.NullValueFoundOnNotNullableField;
import org.excelyus.exceptions.UnknownFileExtension;
import org.excelyus.option.HeaderOptions;

import java.beans.IntrospectionException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 17:12<br/>
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        try {

            HeaderOptions headerOptions = new HeaderOptions();
            headerOptions.setFontHeightInPoints((short)15);
            headerOptions.setColorIndex(IndexedColors.BLUE.index);
            headerOptions.setBold(true);
            ExcelCreator excelCreator = new ExcelCreator(null,null);
            Customer customer = new Customer();
            customer.setName("Batuhan");
            customer.setSurname("Apaydın");
            Customer customer2 = new Customer();
            customer2.setName("Ercan");
            customer2.setSurname("Canlier");
            List<Customer> customers = new ArrayList<>();
            customers.add(customer);
            customers.add(customer2);
            byte[] excelFile = excelCreator.createExcelFile(customers);
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\TEST\\Desktop\\text.xls");
            fileOutputStream.write(excelFile);
            fileOutputStream.close();
        } catch (NoExcelFileAnnotationFoundOnClassException e) {
            e.printStackTrace();
        } catch (NullValueFoundOnNotNullableField nullValueFoundOnNotNullableField) {
            nullValueFoundOnNotNullableField.printStackTrace();
        } catch (DifferentObjectTypeThenExpectedException e) {
            e.printStackTrace();
        } catch (UnknownFileExtension unknownFileExtension) {
            unknownFileExtension.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
