package com.example.testfilesgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DsrcInvDTO {
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
