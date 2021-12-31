package model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"ec1CustomerId,ec1ShortName,ec1CompanyName,ec1Name,ec1SpId,eprCustomerId,eprSpId"})

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
