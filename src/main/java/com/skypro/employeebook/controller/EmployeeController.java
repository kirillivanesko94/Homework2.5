package com.skypro.employeebook.controller;

import com.skypro.employeebook.model.Employee;
import com.skypro.employeebook.exception.EmployeeAlreadyAddedException;
import com.skypro.employeebook.exception.EmployeeNotFoundException;
import com.skypro.employeebook.exception.EmployeeStorageIsFullException;
import com.skypro.employeebook.servise.EmployeeServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("employee")

public class EmployeeController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EmployeeStorageIsFullException.class})
    public String handleException(EmployeeStorageIsFullException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException (EmployeeNotFoundException e){
        return String.format("%s %s", HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String handleException(EmployeeAlreadyAddedException e) {
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
    private final EmployeeServiceInterface employeeServiceInterface;

    public EmployeeController(EmployeeServiceInterface employeeServiceInterface) {
        this.employeeServiceInterface = employeeServiceInterface;
    }

    @GetMapping(path = "add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("passport") Integer passport) {

        return employeeServiceInterface.addEmployee(firstName, lastName, passport);
    }

    @GetMapping(path = "remove")
    public String remove(@RequestParam("passport") Integer passport) {

       return employeeServiceInterface.removeEmployee(passport);
    }

    @GetMapping(path = "find")
    public Employee find(@RequestParam("passport") Integer passport) {

        return employeeServiceInterface.findEmployee(passport);
    }

    @GetMapping(path = "getAll")
    public Map<Integer, Employee> getAll(){
        return employeeServiceInterface.printAllEmployees();
    }
}
