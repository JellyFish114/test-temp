package com.example.testfilesgenerator.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"ec1CustomerId,ec1ShortName,ec1CompanyName,ec1Name,ec1SpId,eprCustomerId,eprSpId"})

public class CustomerMappingCsvDTO {

    private String ec1CustomerId;
    private String ec1ShortName;
    private String ec1CompanyName;
    private String ec1Name;
    private String ec1SpId;
    private String eprCustomerId;
    private String eprSpId;
}
