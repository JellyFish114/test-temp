package com.example.testfilesgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"EC1_CUSTOMER_ID","EC1_SHORT_NAME","EC1_COMPANY_NAME","EC1_NAME","EC1_SP_ID","EPR_CUSTOMER_ID","EPR_SP_ID"})
//@JsonPropertyOrder({"EC1_CUSTOMER_ID,EC1_SHORT_NAME,EC1_COMPANY_NAME,EC1_NAME,EC1_SP_ID,EPR_CUSTOMER_ID,EPR_SP_ID"})

public class CustomerMappingCsvDTO {
    @JsonProperty("EC1_CUSTOMER_ID")
    private String ec1CustomerId;
    @JsonProperty("EC1_SHORT_NAME")
    private String ec1ShortName;
    @JsonProperty("EC1_COMPANY_NAME")
    private String ec1CompanyName;
    @JsonProperty("EC1_NAME")
    private String ec1Name;
    @JsonProperty("EC1_SP_ID")
    private String ec1SpId;
    @JsonProperty("EPR_CUSTOMER_ID")
    private String eprCustomerId;
    @JsonProperty("EPR_SP_ID")
    private String eprSpId;
}
