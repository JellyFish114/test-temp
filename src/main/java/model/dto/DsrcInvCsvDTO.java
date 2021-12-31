package model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"documentName", "veniceInvoiceNumber", "aaxCustomerId", "billDocNumber", "serviceCountry",
        "invoiceIssueDate", "billingPeriodFrom", "billingPeriodTo", "salesPartnerId",
        "paymentReference", "totalNet", "totalGross", "currency"})

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
