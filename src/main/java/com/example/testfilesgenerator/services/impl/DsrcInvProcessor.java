package com.example.testfilesgenerator.services.impl;

import com.example.testfilesgenerator.dto.CustomerMappingCsvDTO;
import com.example.testfilesgenerator.dto.DsrcInvCsvDTO;
import com.example.testfilesgenerator.services.ICsvProcessor;
import com.example.testfilesgenerator.utils.Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DsrcInvProcessor implements ICsvProcessor {

    @Value("${dsrc_template}")
    private String DSRC_TEMPLATE;
    @Value("${pdf_builder_url}")
    private String PDF_BUILDER_URL;
    @Value("${pdf_builder_url_dvi}")
    private String PDF_BUILDER_URL_DVI;
    @Value("${app.csvConfig.columnSeparator}")
    private char COLUMN_SEPARATOR;
    @Value("${app.csvConfig.quoteChar}")
    private char QUOTE_CHAR;

    @Override
    public <T> List<T> processCsv(String inputDir, String mappingPath, String outputDir) {
        System.out.println("\nProcessing Invoices csvs ...");
        List<File> filesToProcess = Utility.getFilesFromDir(inputDir);
        List<CustomerMappingCsvDTO> mappingList = Utility.getDtoFromCsv(CustomerMappingCsvDTO.class, new File(mappingPath), COLUMN_SEPARATOR, QUOTE_CHAR);

        Utility.createFolderIfNotPresent(outputDir);

        List<DsrcInvCsvDTO> processedDsrcDto = null;

        for (File file : filesToProcess) {
            List<DsrcInvCsvDTO> dsrcDTO = Utility.getDtoFromCsv(DsrcInvCsvDTO.class, file, COLUMN_SEPARATOR, QUOTE_CHAR);

            int sizeBeforeProcessing = dsrcDTO.size();

            System.out.println("Processing file: " + file.getName());

            processedDsrcDto = dsrcDTO.stream().filter((dsrc) -> {
                // 1. replace sales partner id (EPR<->EC1)
                Optional<CustomerMappingCsvDTO> ec1SpId = mappingList.stream().filter((el) -> el.getEprSpId().equals(dsrc.getSalesPartnerId())).findFirst();
                ec1SpId.ifPresent(customerMappingCsvDTO -> dsrc.setSalesPartnerId(customerMappingCsvDTO.getEc1SpId()));

                // 2. replace customer number based on ec1-epr-customer-mapping
                Optional<CustomerMappingCsvDTO> ec1CustomerId = mappingList.stream().filter((ec1) -> ec1.getEprCustomerId().equals(dsrc.getAaxCustomerId())).findFirst();
                if (ec1CustomerId.isPresent()) {
                    dsrc.setAaxCustomerId(ec1CustomerId.get().getEc1CustomerId());
                    return true;
                }
                // 3. if customer number (customer id) is not found on the ec1-epr-customer-mapping delete the line
                return false;
            }).collect(Collectors.toList());

            System.out.println("Done. Total lines: " + sizeBeforeProcessing + ", lines after processing: " + processedDsrcDto.size() + "\n");

            // 4. generate the updated mapping_<billrunid>_<serviceCountry>.csv
            Utility.dtosToCsv(processedDsrcDto, outputDir + "" + file.getName());

            // 5. generate the pdfs
            Utility.getPdfsFromDtos(PDF_BUILDER_URL, outputDir, processedDsrcDto, DSRC_TEMPLATE);

            System.out.println("PDFs created.");

        }

        return null;
    }

}