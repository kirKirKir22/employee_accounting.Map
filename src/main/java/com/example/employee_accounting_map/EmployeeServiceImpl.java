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

    public EmployeeServiceImpl() {
        employeeMap = new HashMap<>();

    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);

        if (employeeMap.size() < MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("превышен лимит количества сотрудников в фирме");
        }
        String key = firstName + lastName;
        if (employeeMap.containsKey(newEmployee)) {
            throw new EmployeeAlreadyAddedException("в коллекции уже есть такой сотрудник");
        }
        employeeMap.put(key, newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.remove(firstName + lastName);
        if (!employeeMap.containsValue(employee)) {
            throw new EmployeeNotFoundException("сотрудник не найден");

        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = employeeMap.get(firstName + lastName);

        if (employeeMap.containsKey(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("сотрудник не найден");

    }

    @Override
    public Collection<Employee> printAll() {
        return employeeMap.values();
    }

}