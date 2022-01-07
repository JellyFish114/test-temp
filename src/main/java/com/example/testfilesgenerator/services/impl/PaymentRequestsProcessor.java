package com.example.testfilesgenerator.services.impl;

import com.example.testfilesgenerator.dto.BillableItemCsvDto;
import com.example.testfilesgenerator.dto.CustomerMappingCsvDTO;
import com.example.testfilesgenerator.services.ICsvProcessor;
import com.example.testfilesgenerator.utils.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PaymentRequestsProcessor implements ICsvProcessor {
    @Value("${app.csvConfig.columnSeparator}")
    private char COLUMN_SEPARATOR;
    @Value("${app.csvConfig.quoteChar}")
    private char QUOTE_CHAR;

    @Override
    public List processCsv(String inputDir, String mappingPath, String outputDir) {
        System.out.println("\nProcessing Payment requests csvs ...");

        List<File> filesToProcess = Utility.getCsvsFromDir(inputDir);
        List<CustomerMappingCsvDTO> mappingList = Utility.getDtoFromCsv(CustomerMappingCsvDTO.class, new File(mappingPath),COLUMN_SEPARATOR,QUOTE_CHAR);
        System.out.println(mappingList.size());
        Utility.createFolderIfNotPresent(outputDir);

        for (File file : filesToProcess) {
            System.out.println("Processing file: " + file.getName());

            List<BillableItemCsvDto> paymentRequestsDTO = Utility.getDtoFromCsv(BillableItemCsvDto.class, file,COLUMN_SEPARATOR,QUOTE_CHAR);

            int sizeBeforeProcessing = paymentRequestsDTO.size();

            List<BillableItemCsvDto> processedPaymentRequestsDTO = paymentRequestsDTO.stream().filter((payment) -> {

                Optional<CustomerMappingCsvDTO> ec1SpId = mappingList.stream().filter((el) -> el.getEprSpId().equals(payment.getSalesPartnerAaxId())).findFirst();
                ec1SpId.ifPresent(customerMappingCsvDTO -> payment.setSalesPartnerAaxId(customerMappingCsvDTO.getEc1SpId()));

                Optional<CustomerMappingCsvDTO> ec1CustomerId = mappingList.stream().filter((ec1) -> ec1.getEprCustomerId().equals(payment.getCustomerId())).findFirst();
                if (ec1CustomerId.isPresent()) {
                    payment.setCustomerId(ec1CustomerId.get().getEc1CustomerId());
                    return true;
                }
                return false;

            }).collect(Collectors.toList());

            if(!processedPaymentRequestsDTO.isEmpty()) {
                Utility.dtosToCsv(processedPaymentRequestsDTO, outputDir + "" + file.getName());
            }
            System.out.println("Done. Total lines: " + sizeBeforeProcessing + ", lines after processing: " + processedPaymentRequestsDTO.size() + "\n");

        }

        return null;
    }
}
