package com.example.sellWebApp.service;

import com.example.sellWebApp.dto.ProductDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.ProductDto;
import com.example.sellWebApp.dto.criteria.ProductSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.dto.criteria.ProductSearchCriteria;
import com.example.sellWebApp.entity.Product;
import com.example.sellWebApp.entity.Product;
import com.example.sellWebApp.exception.BusinessException;
import com.example.sellWebApp.exception.EntityType;
import com.example.sellWebApp.exception.ExceptionType;
import com.example.sellWebApp.repository.ProductRepository;
import com.example.sellWebApp.repository.ProductRepository;
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
public class ProductService implements MasterService<ProductDto, Long, ProductSearchCriteria> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductDto getById(Long id) {
        Product employee = repository.findById(id)
                .orElseThrow(() -> BusinessException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id)));

        return modelMapper.map(employee, ProductDto.class);
    }

    @Override
    public ProductDto create(ProductDto model) {
//        Optional<Product> findEmp = repository.findById(model.getId());
//        if (findEmp.isPresent() && !"D".equals(findEmp.get().getFunction())) {
//            throw BusinessException.throwException(EntityType.PRODUCT, ExceptionType.DUPLICATE_ENTITY, model.getUsername());
//        }

        Product entity = modelMapper.map(model, Product.class);
        Product saved = repository.save(entity);
        return modelMapper.map(saved, ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto model) {
        Product existingEmp =  repository.findById(model.getProductId())
                .filter(emp -> !"D".equals(emp.getFunction()))
                .orElseThrow(() -> BusinessException.throwException(
                        EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(model.getProductId())));

        // อัปเดตเฉพาะ field ที่แก้ไข
        existingEmp.setName(model.getName());
        existingEmp.setAmount(model.getAmount());
        existingEmp.setAmountMember(model.getAmountMember());
        existingEmp.setFunction("E");
        Product saved = repository.save(existingEmp);
        return modelMapper.map(saved, ProductDto.class);
    }


    @Override
    public void deleteById(Long id) {
        Optional<Product> findEmp = repository.findById(id);
        if (findEmp.isEmpty() || "D".equals(findEmp.get().getFunction())) {
            throw BusinessException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND, String.valueOf(id));
        }
    }

    @Override
    public PageDto<ProductDto> getAll(PageCriteria<ProductSearchCriteria> condition) {
        // ดึงข้อมูลจาก repository
        Page<Product> empAll = repository.findAll(condition.getSpecification(), condition.genPageRequest());

        // แปลง List<Employee> เป็น List<ProductDto> โดยใช้ modelMapper
        List<ProductDto> collect = empAll.stream()
                .map(data -> modelMapper.map(data, ProductDto.class))
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
