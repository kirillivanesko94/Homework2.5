package com.skypro.employeebook.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String firstName, String lastName) {
        super("Сотрудник " + firstName + " " + lastName + " не найден");
    }
}
