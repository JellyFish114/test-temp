package com.example.testfilesgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"Bill period end", "Billdoc aax id", "Sales partner aax id", "Customer aax id", "Customer id", "Orderable product name", "Base product name",
        "Country code", "Billable item id", "Bill Documentnet amount", "Amount", "Amount perCustomer / region",
})
public class BillableItemCsvDto {
    @JsonProperty("Bill period end")
    private String billPeriodEnd;
    @JsonProperty("Billdoc aax id")
    private String billdocAaxId;
    @JsonProperty("Sales partner aax id")
    private String salesPartnerAaxId;
    @JsonProperty("Customer aax id")
    private String customerAaxId;
    @JsonProperty("Customer id")
    private String customerId;
    @JsonProperty("Orderable product name")
    private String orderableProductName;
    @JsonProperty("Base product name")
    private String baseProductName;
    @JsonProperty("Country code")
    private String countryCode;
    @JsonProperty("Billable item id")
    private String billableItemId;
    @JsonProperty("Bill Documentnet amount")
    private String billDocumentnetAmount;
    @JsonProperty("Amount")
    private String amount;
    @JsonProperty("Amount perCustomer / region")
    private String amountPerCustomerRegion;

}
