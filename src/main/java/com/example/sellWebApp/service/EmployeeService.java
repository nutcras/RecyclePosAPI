package com.example.sellWebApp.service;

import com.example.sellWebApp.dto.EmployeeDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.criteria.EmployeeSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.entity.Employee;
import com.example.sellWebApp.enums.FunctionEnum;
import com.example.sellWebApp.exception.BusinessException;
import com.example.sellWebApp.exception.EntityType;
import com.example.sellWebApp.exception.ExceptionType;
import com.example.sellWebApp.repository.EmployeeRepository;
import com.example.sellWebApp.service.common.MasterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService implements MasterService<EmployeeDto, Long, EmployeeSearchCriteria> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository repository;

    @Override
    public EmployeeDto getById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> BusinessException.throwException(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id)));

        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto create(EmployeeDto model) {
        Optional<Employee> findEmp = Optional.ofNullable(repository.findByUsername(model.getUsername()));
        if (findEmp.isPresent() && !"D".equals(findEmp.get().getFunction())) {
            throw BusinessException.throwException(EntityType.EMPLOYEE, ExceptionType.DUPLICATE_ENTITY, model.getUsername());
        }

        Employee entity = modelMapper.map(model, Employee.class);
        Employee saved = repository.save(entity);
        return modelMapper.map(saved, EmployeeDto.class);
    }

    @Override
    public EmployeeDto update(EmployeeDto model) {
        Employee existingEmp = Optional.ofNullable(repository.findByUsername(model.getUsername()))
                .filter(emp -> !"D".equals(emp.getFunction()))
                .orElseThrow(() -> BusinessException.throwException(
                        EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND, model.getUsername()));

        // อัปเดตเฉพาะ field ที่แก้ไข
        existingEmp.setName(model.getName());
        existingEmp.setPassword(model.getPassword());
        existingEmp.setRole(model.getRole());
        existingEmp.setFunction("E");
        Employee saved = repository.save(existingEmp);
        return modelMapper.map(saved, EmployeeDto.class);
    }


    @Override
    public void deleteById(Long id) {
        Optional<Employee> findEmp = repository.findById(id);
        if (findEmp.isEmpty() || "D".equals(findEmp.get().getFunction())) {
            throw BusinessException.throwException(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
        }
        repository.updateFunctionByUsername("D",findEmp.get().getUsername());
    }

    @Override
    public PageDto<EmployeeDto> getAll(PageCriteria<EmployeeSearchCriteria> condition) {
        // ดึงข้อมูลจาก repository
        Page<Employee> empAll = repository.findAll(condition.getSpecification(), condition.genPageRequest());

        // แปลง List<Employee> เป็น List<EmployeeDto> โดยใช้ modelMapper
        List<EmployeeDto> collect = empAll.stream()
                .map(data -> modelMapper.map(data, EmployeeDto.class))
                .collect(Collectors.toList());

        // คืนค่าผลลัพธ์เป็น PageDto
        return new PageDto<>(
                collect,
                empAll.getSize(),
                empAll.getTotalElements(),
                empAll.getTotalPages(),
                empAll.getNumber()
        );
    }

}
