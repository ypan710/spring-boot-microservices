package net.java.employeeservice.service;

import net.java.employeeservice.dto.EmployeeDto;
import net.java.employeeservice.entity.Employee;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
}
