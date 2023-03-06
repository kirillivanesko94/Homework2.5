package com.skypro.employeebook.exception;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException(String firstName, String lastName) {
        super("Сотрудник: " + firstName + " " + lastName + " уже добавлен в хранилище");
    }
}
