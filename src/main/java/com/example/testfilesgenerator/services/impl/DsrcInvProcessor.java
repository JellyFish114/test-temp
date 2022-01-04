package com.example.testfilesgenerator.services.impl;

import com.example.testfilesgenerator.dto.CustomerMappingCsvDTO;
import com.example.testfilesgenerator.dto.DsrcInvCsvDTO;
import com.example.testfilesgenerator.utils.Utility;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Component
public class DsrcInvProcessor  {

    public <T> List<T> processCsv(String inputDir, String mappingPath, String outputDir, String pdf_builder_url, String template) {
        System.out.println(pdf_builder_url);

        List<File> filesToProcess = Utility.getFilesFromDir(inputDir);
        List<CustomerMappingCsvDTO> mappingList = Utility.getDtoFromCsv(CustomerMappingCsvDTO.class, new File(mappingPath));

        Utility.createFolderIfNotPresent(outputDir);

        List<DsrcInvCsvDTO> processedDsrcDto = null;

        for (File file : filesToProcess) {
            List<DsrcInvCsvDTO> dsrcDTO = Utility.getDtoFromCsv(DsrcInvCsvDTO.class, file);

            int sizeBeforeProcessing = dsrcDTO.size();

            System.out.println("processing file: " + file.getName());

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
            }).toList();

            System.out.println("Finished.\n Total lines: " + sizeBeforeProcessing + "\n Lines after processing: " + processedDsrcDto.size() + "\n");

            // 4. generate the updated mapping_<billrunid>_<serviceCountry>.csv
            Utility.dtosToCsv(processedDsrcDto, outputDir + "" + file.getName());

            // 5. generate the pdfs
            Utility.getPdfsFromDtos(pdf_builder_url, outputDir, processedDsrcDto, template);

        }

        return null;
    }

}