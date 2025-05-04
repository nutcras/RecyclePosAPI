package com.example.sellWebApp.service;

import com.example.sellWebApp.dto.SaleDetailDto;
import com.example.sellWebApp.dto.SaleDto;
import com.example.sellWebApp.entity.Sale;
import com.example.sellWebApp.entity.SaleDetail;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generateReportPdf(SaleDto sale, List<SaleDetailDto> detailedSaleDetails) {
        try {
            // Load the JRXML file
            InputStream reportStream = getClass().getResourceAsStream("/report/saleReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Set report parameters
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("company", "ร้านกะดะห์ค้าของเก่า");
            parameters.put("address", "167");
            parameters.put("name", sale.getMember().getName());
            parameters.put("memberId", sale.getMember().getMemberId().toString());
            parameters.put("phone", sale.getMember().getPhoneNumber());

            // Prepare data source from sale details
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(detailedSaleDetails);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export to PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
