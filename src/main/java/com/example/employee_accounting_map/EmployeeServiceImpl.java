package com.example.employee_accounting_map;

import com.example.employee_accounting_map.exception.EmployeeAlreadyAddedException;
import com.example.employee_accounting_map.exception.EmployeeNotFoundException;
import com.example.employee_accounting_map.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final Map<String, Employee> employeeMap;
    private static final int MAX_EMPLOYEES = 100;

    private String generateKey(String firstName, String lastName) {
        return firstName + lastName;
    }


    public EmployeeServiceImpl() {
        employeeMap = new HashMap<>();

    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (employeeMap.size() > MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }
        String key = generateKey(firstName, lastName);
        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("в коллекции уже есть такой сотрудник");
        }
        employeeMap.put(key, newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.remove(firstName + lastName);
        String key = generateKey(firstName, lastName);
        if (!employeeMap.containsValue(key)) {
            throw new EmployeeNotFoundException("сотрудник не найден");

        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.get(firstName + lastName);
        String key = generateKey(firstName, lastName);

        if (!employeeMap.containsKey(key)) {
            throw new EmployeeNotFoundException("сотрудник не найден");
        }
        return employee;

    }

    @Override
    public Collection<Employee> printAll() {
        return employeeMap.values();
    }

}

