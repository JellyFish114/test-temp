package com.example.testfilesgenerator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"billPeriodEnd", "billdocAaxId", "salesPartnerAaxId", "customerAaxId", "customerId", "orderableProductName", "baseProductName",
        "countryCode", "billableItemId", "billDocumentnetAmount", "amount", "amountPerCustomerRegion",
})
public class BillableItemCsvDto {

    private String billdocAaxId;
    private String billPeriodEnd;
    private String customerId;
    private String countryCode;
    private String amount;
    private String billableItemId;
    private String amountPerCustomerRegion;
    private String salesPartnerAaxId;
    private String customerAaxId;
    private String orderableProductName;
    private String baseProductName;
    private String billDocumentnetAmount;

}
