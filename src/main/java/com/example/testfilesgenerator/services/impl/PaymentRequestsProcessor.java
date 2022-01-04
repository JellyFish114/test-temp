package com.example.testfilesgenerator.services.impl;

import com.example.testfilesgenerator.dto.BillableItemCsvDto;
import com.example.testfilesgenerator.dto.CustomerMappingCsvDTO;
import com.example.testfilesgenerator.services.ICsvProcessor;
import com.example.testfilesgenerator.utils.Utility;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Component
public class PaymentRequestsProcessor implements ICsvProcessor {
    @Override
    public List processCsv(String inputDir, String mappingPath, String outputDir) {
        List<File> filesToProcess = Utility.getFilesFromDir(inputDir);
        List<CustomerMappingCsvDTO> mappingList = Utility.getDtoFromCsv(CustomerMappingCsvDTO.class, new File(mappingPath));

        File theDir = new File(outputDir);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        for (File file : filesToProcess) {
            System.out.println("processing file: " + file.getName());

            List<BillableItemCsvDto> paymentRequestsDTO = Utility.getDtoFromCsv(BillableItemCsvDto.class, file);
            System.out.println(paymentRequestsDTO.size());


            List<BillableItemCsvDto> processedPaymentRequestsDTO = paymentRequestsDTO.stream().filter((payment) -> {
                Optional<CustomerMappingCsvDTO> ec1SpId = mappingList.stream().filter((el) -> el.getEprSpId().equals(payment.getSalesPartnerAaxId())).findFirst();
                ec1SpId.ifPresent(customerMappingCsvDTO -> payment.setSalesPartnerAaxId(customerMappingCsvDTO.getEc1SpId()));

                Optional<CustomerMappingCsvDTO> ec1CustomerId = mappingList.stream().filter((ec1) -> ec1.getEprCustomerId().equals(payment.getCustomerId())).findFirst();
                if (ec1CustomerId.isPresent()) {
                    payment.setCustomerId(ec1CustomerId.get().getEc1CustomerId());
                    return true;
                }
                return false;
            }).toList();

            Utility.dtosToCsv(processedPaymentRequestsDTO, outputDir + "" + file.getName());

        }

        return null;
    }
}
