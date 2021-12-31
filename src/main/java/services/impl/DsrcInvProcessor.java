package services.impl;


//import exception.CsvParsingException;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import model.dto.CustomerMappingCsvDTO;
import model.dto.DsrcInvCsvDTO;
import org.springframework.stereotype.Component;
import services.ICsvProcessor;
import utils.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DsrcInvProcessor implements ICsvProcessor {

    @Override
    public Map<String, String> processCsv(String dirName, String mappingPath) {

        List<File> filesToProcess = Utility.getFilesFromDir(dirName);
        List<CustomerMappingCsvDTO> mappingList = (List<CustomerMappingCsvDTO>) Utility.getDtoFromCsv(CustomerMappingCsvDTO.class, new File(mappingPath));

        filesToProcess.forEach((file) -> {
            processDsrc(file, mappingList);
        });

        return null;
    }

    private void processDsrc(File file, List<CustomerMappingCsvDTO> mappingList) {
        List<DsrcInvCsvDTO> dsrcDTO = (List<DsrcInvCsvDTO>) Utility.getDtoFromCsv(DsrcInvCsvDTO.class, file);
        List<DsrcInvCsvDTO> processedDsrcDto = new ArrayList<>();
        int sizeBeforeProcessing = dsrcDTO.size();

        System.out.println("processing file: " + file.getName());

        dsrcDTO.stream().forEach((dsrc) -> {
            // 1. replace sales partner id (EPR<->EC1)
            Optional<CustomerMappingCsvDTO> ec1SpId = mappingList.stream().filter((el) -> el.getEprSpId().equals(dsrc.getSalesPartnerId())).findFirst();
            if (ec1SpId.isPresent())
                dsrc.setSalesPartnerId(ec1SpId.get().getEc1SpId());

            // 2. replace customer number based on ec1-epr-customer-mapping
            Optional<CustomerMappingCsvDTO> ec1CustomerId = mappingList.stream().filter((el) -> el.getEprCustomerId().equals(dsrc.getAaxCustomerId())).findFirst();
            if (ec1CustomerId.isPresent())
                dsrc.setAaxCustomerId(ec1CustomerId.get().getEc1CustomerId());
            else {
                dsrc.setAaxCustomerId("");
            }
        });

        // 3. if customer number (customer id) is not found on the ec1-epr-customer-mapping delete the line
        processedDsrcDto = dsrcDTO.stream().filter(dsrc -> !dsrc.getAaxCustomerId().equals("")).collect(Collectors.toList());
        System.out.println("Finished.\n Total lines:"+sizeBeforeProcessing+"\n Lines after processing: " + processedDsrcDto.size());

        // 4. generate the updated mapping_<billrunid>_<serviceCountry>.csv

    }

    // TODO: maybe move to an other class
    public void generatePDFs() {
        //5. create a template for the new PDFs on Doc-builder
        //6. generate the PDFs
    }
}