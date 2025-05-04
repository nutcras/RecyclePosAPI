package com.example.sellWebApp.service;

import com.example.sellWebApp.dto.AttemptResellDto;
import com.example.sellWebApp.dto.MemberDto;
import com.example.sellWebApp.dto.PageDto;
import com.example.sellWebApp.dto.criteria.AttemptResellSearchCriteria;
import com.example.sellWebApp.dto.criteria.PageCriteria;
import com.example.sellWebApp.entity.AttemptResell;
import com.example.sellWebApp.repository.AttemptResellRepository;
import com.example.sellWebApp.repository.SaleDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AttemptResellService {

    @Autowired
    private AttemptResellRepository repository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AttemptResellDto createAttemptResell() {
        // Find the last attempt record
        AttemptResell lastAttempt = repository.findLastAttempt();

        // If no last attempt, use date from one year ago
        Date lastDate;
        if (lastAttempt != null) {
            lastDate = lastAttempt.getEndDate();
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            lastDate = calendar.getTime();
        }

        Date currentDate = new Date();

        // Get total amount and weight for unit W
        Float amountW = saleDetailRepository.sumAmount(lastDate, currentDate, "W");
        Float weightW = saleDetailRepository.sumWeight(lastDate, currentDate, "W");

        // Get total amount and weight for unit Q
        Float amountQ = saleDetailRepository.sumAmount(lastDate, currentDate, "Q");
        Float weightQ = saleDetailRepository.sumWeight(lastDate, currentDate, "Q");

        // Calculate totals with null handling


        Float totalAmount = (float) ((amountW != null ? amountW : 0) + (amountQ != null ? amountQ : 0));
        Float totalWeight = weightW != null ? (float) weightW : 0;
        Float totalQuantity = weightQ != null ? (float) weightQ : 0;

        // Create new attempt resell
        AttemptResell AttemptResell = new AttemptResell();
        AttemptResell.setStartDate(lastDate);
        AttemptResell.setEndDate(currentDate);
        AttemptResell.setAmount(totalAmount);
        AttemptResell.setWeight(totalWeight);
        AttemptResell.setQuantity(totalQuantity);
        AttemptResell res =  repository.save(AttemptResell);
        return modelMapper.map(res, AttemptResellDto.class);
    }

    public PageDto<AttemptResellDto> getAll(PageCriteria<AttemptResellSearchCriteria> condition) {

       Page<AttemptResell> page = repository.findAll(condition.getSpecification(), condition.genPageRequest());

        // แปลง List<Employee> เป็น List<MemberDto> โดยใช้ modelMapper
        List<AttemptResellDto> collect = page.stream()
                .map(data -> modelMapper.map(data, AttemptResellDto.class))
                .collect(Collectors.toList());

        return new PageDto<>(
                collect,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber()
        );
    }
}
