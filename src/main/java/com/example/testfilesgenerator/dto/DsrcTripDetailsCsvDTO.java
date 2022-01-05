package com.example.testfilesgenerator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@Data
@JsonPropertyOrder({"billRunId", "billableItemId", "billingPeriodStartDate", "billingPeriodEndDate",
        "serviceCountry", "baseProductId", "baseProductName", "businessPartnerId", "customerNo", "externalBillingReference", "lpn",
        "pan", "transactionId", "entryDate", "entryTime", "entryNumber", "entryName", "description", "exitDate", "exitTime", "exitNumber",
        "exitName", "vatRate", "currency", "chargedAmountNet", "chargedAmountGross", "billRunId2"})

public class DsrcTripDetailsCsvDTO {

    private String billRunId;
    private String billableItemId;
    private String billingPeriodStartDate;
    private String billingPeriodEndDate;
    private String serviceCountry;
    private String baseProductId;
    private String baseProductName;
    private String businessPartnerId;
    private String customerNo;
    private String externalBillingReference;
    private String lpn;
    private String pan;
    private String transactionId;
    private String entryDate;
    private String entryTime;
    private String entryNumber;
    private String entryName;
    private String description;
    private String exitDate;
    private String exitTime;
    private String exitNumber;
    private String exitName;
    private String vatRate;
    private String currency;
    private String chargedAmountNet;
    private String chargedAmountGross;
    private String billRunId2;
}
