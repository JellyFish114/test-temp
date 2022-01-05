package com.example.testfilesgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"documentName", "veniceInvoiceNumber", "aaxCustomerId", "billDocNumber", "serviceCountry",
        "invoiceIssueDate", "billingPeriodFrom", "billingPeriodTo", "salesPartnerId",
        "paymentReference", "totalNet", "totalGross", "currency"})

public class DsrcInvCsvDTO {
    private String documentName;
    private String veniceInvoiceNumber;
    private String aaxCustomerId;
    private String billDocNumber;
    private String serviceCountry;
    private String invoiceIssueDate;
    private String billingPeriodFrom;
    private String billingPeriodTo;
    private String salesPartnerId;
    private String paymentReference;
    private String totalNet;
    private String totalGross;
    private String currency;
}
