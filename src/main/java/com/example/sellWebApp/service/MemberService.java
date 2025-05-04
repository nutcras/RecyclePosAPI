package com.example.sellWebApp.service;

import com.example.sellWebApp.dto.MemberDto;
import com.example.sellWebApp.dto.MemberDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.criteria.EmployeeSearchCriteria;
import com.example.sellWebApp.dto.criteria.MemberSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.entity.Employee;
import com.example.sellWebApp.entity.Member;
import com.example.sellWebApp.exception.BusinessException;
import com.example.sellWebApp.exception.EntityType;
import com.example.sellWebApp.exception.ExceptionType;
import com.example.sellWebApp.repository.EmployeeRepository;
import com.example.sellWebApp.repository.MemberRepository;
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
public class MemberService implements MasterService<MemberDto, Long, MemberSearchCriteria> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository repository;

    @Override
    public MemberDto getById(Long id) {
        Member employee = repository.findById(id)
                .orElseThrow(() -> BusinessException.throwException(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id)));

        return modelMapper.map(employee, MemberDto.class);
    }

    @Override
    public MemberDto create(MemberDto model) {
//        Optional<Member> findEmp = repository.findById(model.getId());
//        if (findEmp.isPresent() && !"D".equals(findEmp.get().getFunction())) {
//            throw BusinessException.throwException(EntityType.EMPLOYEE, ExceptionType.DUPLICATE_ENTITY, model.getUsername());
//        }

        Member entity = modelMapper.map(model, Member.class);
        Member saved = repository.save(entity);
        return modelMapper.map(saved, MemberDto.class);
    }

    @Override
    public MemberDto update(MemberDto model) {
        Member existingEmp =  repository.findById(model.getMemberId())
                .filter(emp -> !"D".equals(emp.getFunction()))
                .orElseThrow(() -> BusinessException.throwException(
                        EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(model.getMemberId())));

        // อัปเดตเฉพาะ field ที่แก้ไข
        existingEmp.setName(model.getName());
        existingEmp.setPhoneNumber(model.getPhoneNumber());
        existingEmp.setFunction("E");
        Member saved = repository.save(existingEmp);
        return modelMapper.map(saved, MemberDto.class);
    }


    @Override
    public void deleteById(Long id) {
        Optional<Member> findEmp = repository.findById(id);
        if (findEmp.isEmpty() || "D".equals(findEmp.get().getFunction())) {
            throw BusinessException.throwException(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
        }
        repository.updateFunctionByMemberId("D", id);
    }

    @Override
    public PageDto<MemberDto> getAll(PageCriteria<MemberSearchCriteria> condition) {
        // ดึงข้อมูลจาก repository
        Page<Member> empAll = repository.findAll(condition.getSpecification(), condition.genPageRequest());

        // แปลง List<Employee> เป็น List<MemberDto> โดยใช้ modelMapper
        List<MemberDto> collect = empAll.stream()
                .map(data -> modelMapper.map(data, MemberDto.class))
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
