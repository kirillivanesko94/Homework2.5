package com.skypro.employeebook.exception;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(int storageSize) {
        super("Хранилище (" + storageSize + " элементов) полностью заполнено");
    }
}
