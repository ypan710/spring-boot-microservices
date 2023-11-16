package net.java.departmentservice.service.Impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.java.departmentservice.dto.DepartmentDto;
import net.java.departmentservice.entity.Department;
import net.java.departmentservice.exception.ResourceNotFoundException;
import net.java.departmentservice.repository.DepartmentRepository;
import net.java.departmentservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // convert Entity into DTO
    private DepartmentDto mapToDTO(Department department){
        DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);
        return departmentDto;
    }

    // convert DTO to Entity
    private Department mapToEntity(DepartmentDto departmentDto){
        Department department = modelMapper.map(departmentDto, Department.class);
        return department;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        // convert department dto to department jpa entity
        Department department = mapToEntity(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        // convert department jpa entity to department dto
        DepartmentDto savedDepartmentDto = mapToDTO(savedDepartment);
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {

        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        if (department == null) {
            throw new ResourceNotFoundException("Department", "Code", departmentCode);
        }
        // convert department jpa entity to department dto
        DepartmentDto departmentDto = mapToDTO(department);
        return departmentDto;
    }
}
