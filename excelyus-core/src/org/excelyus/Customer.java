package org.excelyus;

import org.excelyus.annotations.Column;
import org.excelyus.annotations.Excel;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: Batuhan Apaydin<br/>
 * Date: 13.10.2016<br/>
 * Time: 17:12<br/>
 * To change this template use File | Settings | File Templates.
 */
@Excel(format = "xls")
public class Customer {

    @Column(columnName = "Adı",nullable = false)
    private String name;

    @Column(columnName = "Soyadı",nullable = true)
    private String surname;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
