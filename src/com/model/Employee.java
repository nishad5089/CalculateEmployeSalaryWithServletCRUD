package com.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {
    private int id;
    private String name;
    private int grade;
    private String bankAccountNumber;
    private String mobileNo;
    private String address;
}
