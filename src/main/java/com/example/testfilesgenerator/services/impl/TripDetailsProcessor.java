package com.example.testfilesgenerator.services.impl;

import com.example.testfilesgenerator.dto.*;
import com.example.testfilesgenerator.utils.Utility;
import com.example.testfilesgenerator.services.ICsvProcessor;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TripDetailsProcessor implements ICsvProcessor {

    @Value("${app.csvConfig.columnSeparator}")
    private char COLUMN_SEPARATOR;
    @Value("${app.csvConfig.quoteChar}")
    private char QUOTE_CHAR;

    @Override
    public List processCsv(String inputDir, String mappingPath, String outputDir) {
        System.out.println("\nProcessing Trip details csvs ...");

        List<File> filesToProcess = Utility.getFilesFromDir(inputDir);
        List<CustomerMappingCsvDTO> mappingList = Utility.getDtoFromCsv(CustomerMappingCsvDTO.class, new File(mappingPath), COLUMN_SEPARATOR, QUOTE_CHAR);

        Utility.createFolderIfNotPresent(outputDir);

        for (File file : filesToProcess) {
            System.out.println("Processing file: " + file.getName());

            List<DsrcTripDetailsCsvDTO> tripDetailsDTO = Utility.getDtoFromCsv(DsrcTripDetailsCsvDTO.class, file, COLUMN_SEPARATOR, QUOTE_CHAR);

            int sizeBeforeProcessing = tripDetailsDTO.size();

            List<DsrcTripDetailsCsvDTO> processedTripDetailsDTO = tripDetailsDTO.stream().filter((tripdetails) -> {

                Optional<CustomerMappingCsvDTO> ec1SpId = mappingList.stream().filter((el) -> el.getEprSpId().equals(tripdetails.getBusinessPartnerId())).findFirst();
                ec1SpId.ifPresent(customerMappingCsvDTO -> tripdetails.setBusinessPartnerId(customerMappingCsvDTO.getEc1SpId()));

                Optional<CustomerMappingCsvDTO> ec1CustomerId = mappingList.stream().filter((ec1) -> ec1.getEprCustomerId().equals(tripdetails.getCustomerNo())).findFirst();
                if (ec1CustomerId.isPresent()) {
                    tripdetails.setCustomerNo(ec1CustomerId.get().getEc1CustomerId());
                    return true;
                }
                return false;

            }).collect(Collectors.toList());

            // Remove first column Billrun ID2 and write to CSV using a mixin and mapper
            CsvMapper csvMapper = new CsvMapper();

            csvMapper.addMixIn(DsrcTripDetailsCsvDTO.class, DsrcTripDetailsCsvMixin.class);

            CsvSchema csvSchema = csvMapper
                    .typedSchemaFor(DsrcTripDetailsCsvMapper.class).withStrictHeaders(true)
                    .withHeader()
                    .withColumnSeparator(COLUMN_SEPARATOR)
                    .withQuoteChar(QUOTE_CHAR)
                    .withComments();

            try {
                SequenceWriter dtoItr = csvMapper.writerWithSchemaFor(DsrcTripDetailsCsvDTO.class)
                        .with(csvSchema)
                        .writeValues(new File(outputDir + file.getName()));

                dtoItr.writeAll(processedTripDetailsDTO);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Done. Total lines: " + sizeBeforeProcessing + ", lines after processing: " + processedTripDetailsDTO.size() + "\n");

        }

        return null;
    }
}
