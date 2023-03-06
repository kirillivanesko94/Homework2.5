package com.skypro.employeebook.servise;

import com.skypro.employeebook.employee.Employee;
import com.skypro.employeebook.exception.EmployeeAlreadyAddedException;
import com.skypro.employeebook.exception.EmployeeNotFoundException;
import com.skypro.employeebook.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final int MAX_COUNT = 2;
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == MAX_COUNT) {
            throw new EmployeeStorageIsFullException(employees.size());
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException(firstName, lastName);
        }
        employees.add(employee);

        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee e : employees) {
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                Employee employee;
                employee = e;
                return employee;
            }
        }
        throw new EmployeeNotFoundException(firstName, lastName);
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        employees.remove(employee);
        return employee;
    }


    public List<Employee> printAllEmployees() {
        return employees;
    }

}
