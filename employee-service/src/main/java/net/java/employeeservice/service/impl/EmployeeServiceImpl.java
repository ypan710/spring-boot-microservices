package net.java.employeeservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.java.employeeservice.dto.EmployeeDto;
import net.java.employeeservice.entity.Employee;
import net.java.employeeservice.exception.ResourceNotFoundException;
import net.java.employeeservice.repository.EmployeeRepository;
import net.java.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper;
    private EmployeeRepository employeeRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // convert Entity into DTO
    private EmployeeDto mapToDTO(Employee employee){
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    // convert DTO to entity
    private Employee mapToEntity(EmployeeDto employeeDto){
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        return employee;
    }
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = modelMapper.map(employeeDto, Employee.class);
//        Employee employee = new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail()
//        );

        Employee saveEmployee = employeeRepository.save(employee);

        EmployeeDto saveEmployeeDto = modelMapper.map(saveEmployee, EmployeeDto.class);
//        EmployeeDto saveEmployeeDto = new EmployeeDto(
//                saveEmployee.getId(),
//                saveEmployee.getFirstName(),
//                saveEmployee.getLastName(),
//                saveEmployee.getEmail()
//        );
        return saveEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", employeeId));

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail()
//        );
        return employeeDto;
    }
}
