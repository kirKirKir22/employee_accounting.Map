package com.example.employee_accounting_map.exception;

public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException(String  message) {
        super(message);
    }
}
