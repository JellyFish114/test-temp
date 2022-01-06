package com.example.testfilesgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({"DocumentName", "Venice Invoice No", "AAX Customer ID", "Bill document number", "Service Country",
        "Invoice Issue Date", "Billing Period from", "Billing Period to", "Sales Partner ID",
        "Payment Reference", "Total Net", "Total Gross", "Currency"})

public class DsrcInvCsvDTO {
    @JsonProperty("DocumentName")
    private String documentName;
    @JsonProperty("Venice Invoice No")
    private String veniceInvoiceNumber;
    @JsonProperty("AAX Customer ID")
    private String aaxCustomerId;
    @JsonProperty("Bill document number")
    private String billDocNumber;
    @JsonProperty("Service Country")
    private String serviceCountry;
    @JsonProperty("Invoice Issue Date")
    private String invoiceIssueDate;
    @JsonProperty("Billing Period from")
    private String billingPeriodFrom;
    @JsonProperty("Billing Period to")
    private String billingPeriodTo;
    @JsonProperty("Sales Partner ID")
    private String salesPartnerId;
    @JsonProperty("Payment Reference")
    private String paymentReference;
    @JsonProperty("Total Net")
    private String totalNet;
    @JsonProperty("Total Gross")
    private String totalGross;
    @JsonProperty("Currency")
    private String currency;
}
