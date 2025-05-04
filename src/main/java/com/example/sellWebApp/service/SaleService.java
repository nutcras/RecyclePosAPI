package com.example.sellWebApp.service;

import com.example.sellWebApp.dto.EmployeeDto;
import com.example.sellWebApp.dto.MemberDto;
import com.example.sellWebApp.dto.SaleDetailDto;
import com.example.sellWebApp.dto.SaleDto;
import com.example.sellWebApp.entity.*;
import com.example.sellWebApp.exception.BusinessException;
import com.example.sellWebApp.exception.EntityType;
import com.example.sellWebApp.exception.ExceptionType;
import com.example.sellWebApp.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private SaleDetailRepository detailRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public SaleDto create(SaleDto request) {
        // 1. ดึง employee และ member (optional)
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> BusinessException.throwException(EntityType.EMPLOYEE, ExceptionType.ENTITY_NOT_FOUND,
                        String.valueOf(request.getEmployeeId())));

        // ไม่ควรกำหนดค่า Optional เป็น null
        Optional<Member> memberOptional = Optional.empty();
        if (request.getMemberId() != null) {
            memberOptional = memberRepository.findById(request.getMemberId());
        }

        // 2. สร้าง Sale
        Sale sale = new Sale();
        sale.setDate(LocalDateTime.now());
        sale.setEmployee(request.getEmployeeId()); // เก็บเฉพาะ ID ตามต้องการ
        if (request.getMemberId() != null) {
            sale.setMember(request.getMemberId()); // เก็บเฉพาะ ID ตามต้องการ
        }
        sale = repository.save(sale);

        // สร้าง SaleDto เพื่อส่งกลับ
        SaleDto saleToReport = modelMapper.map(sale, SaleDto.class);
        saleToReport.setEmployee(modelMapper.map(employee, EmployeeDto.class));
        memberOptional.ifPresent(member ->
                saleToReport.setMember(modelMapper.map(member, MemberDto.class)));

        // 3. รวมรายการสินค้าด้วย product_id - ใช้ Stream API เพื่อให้โค้ดกระชับขึ้น
        Map<Long, Double> combined = request.getDetails().stream()
                .collect(Collectors.groupingBy(
                        SaleDetailDto::getProductId,
                        Collectors.summingDouble(detail -> Double.valueOf(detail.getWeight()))
                ));

        // ดึงข้อมูล product ทั้งหมดที่ต้องการในคราวเดียว
        Set<Long> productIds = combined.keySet();
        Map<Long, Product> productMap = productRepository.findAllById(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getProductId, product -> product));

        // สร้าง SaleDetail
        List<SaleDetail> saleDetails = new ArrayList<>();
        for (Map.Entry<Long, Double> entry : combined.entrySet()) {
            Long productId = entry.getKey();
            Double weight = entry.getValue();

            Product product = productMap.get(productId);
            if (product == null) {
                throw BusinessException.throwException(EntityType.PRODUCT, ExceptionType.ENTITY_NOT_FOUND,
                        String.valueOf(productId));
            }

            boolean isMember = memberOptional.isPresent();
            double productAmount = isMember ? product.getAmountMember() : product.getAmount();
            double amount = weight * productAmount;

            SaleDetail detail = new SaleDetail();
            detail.setSale(sale.getSaleId());
            detail.setProductId(productId);
            detail.setWeight(round(weight));
            detail.setAmount(round(amount));
            detail.setProductAmount(round(productAmount));

            saleDetails.add(detail);
        }

        // บันทึก details ทั้งหมด
        detailRepository.saveAll(saleDetails);

        // แปลงข้อมูลและส่งกลับ
        saleToReport.setDetails(saleDetails.stream()
                .map(detail -> modelMapper.map(detail, SaleDetailDto.class))
                .collect(Collectors.toList()));

        return saleToReport;
    }

    private float round(double value) {
        return (float) (Math.round(value * 100.0) / 100.0);
    }

    public byte[] getReport(SaleDto request) {


//        reportService.generateReportPdf();


        return null;
    }
}
