package services.impl;

import model.dto.CustomerMappingCsvDTO;
import model.dto.DsrcTripDetailsCsvDTO;
import services.ICsvProcessor;
import utils.Utility;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TripDetailsProcessor implements ICsvProcessor {
    @Override
    public Map<String, String> processCsv(String inputDir, String mappingPath, String outputDir) {
        List<File> filesToProcess = Utility.getFilesFromDir(inputDir);
        List<CustomerMappingCsvDTO> mappingList = Utility.getDtoFromCsv(CustomerMappingCsvDTO.class, new File(mappingPath));

        File theDir = new File(outputDir);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        for (File file : filesToProcess) {

            List<DsrcTripDetailsCsvDTO> tripDetailsDTO = Utility.getDtoFromCsv(DsrcTripDetailsCsvDTO.class, file);

            System.out.println("processing file: " + file.getName());

            List<DsrcTripDetailsCsvDTO> processedPaymentRequestsDTO = tripDetailsDTO.stream().filter((tripdetails) -> {
                Optional<CustomerMappingCsvDTO> ec1SpId = mappingList.stream().filter((el) -> el.getEprSpId().equals(tripdetails.getBusinessPartnerId())).findFirst();
                ec1SpId.ifPresent(customerMappingCsvDTO -> tripdetails.setBusinessPartnerId(customerMappingCsvDTO.getEc1SpId()));

                Optional<CustomerMappingCsvDTO> ec1CustomerId = mappingList.stream().filter((ec1) -> ec1.getEprCustomerId().equals(tripdetails.getCustomerNo())).findFirst();
                if (ec1CustomerId.isPresent()) {
                    tripdetails.setCustomerNo(ec1CustomerId.get().getEc1CustomerId());
                    return true;
                }
                return false;
            }).toList();

            Utility.dtosToCsv(processedPaymentRequestsDTO, outputDir + "" + file.getName());

        }

        return null;
    }
}
