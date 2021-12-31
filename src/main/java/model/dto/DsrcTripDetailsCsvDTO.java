package model.dto;

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

    @JsonProperty("Bill RunID")
    private String billRunId;

    @JsonProperty("BillableItem ID")
    private String billableItemId;

    @JsonProperty("Billing PeriodStart Date")
    private String billingPeriodStartDate;

    @JsonProperty("Billing PeriodEnd Date")
    private String billingPeriodEndDate;

    @JsonProperty("ServiceCountry")
    private String serviceCountry;

    @JsonProperty("Baseproduct ID")
    private String baseProductId;

    @JsonProperty("Baseproduct Name")
    private String baseProductName;

    @JsonProperty("BusinessPartner ID")
    private String businessPartnerId;

    @JsonProperty("CustomerNo")
    private String customerNo;

    @JsonProperty("ExternalBilling Reference")
    private String externalBillingReference;

    @JsonProperty("LPN")
    private String lpn;

    @JsonProperty("PAN")
    private String pan;

    @JsonProperty("Transaction ID")
    private String transactionId;

    @JsonProperty("Entry Date")
    private String entryDate;

    @JsonProperty("Entry Time")
    private String entryTime;

    @JsonProperty("EntryNumber")
    private String entryNumber;

    @JsonProperty("EntryName")
    private String entryName;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Exit Date")
    private String exitDate;

    @JsonProperty("Exit Time")
    private String exitTime;

    @JsonProperty("Exit Number")
    private String exitNumber;

    @JsonProperty("Exit Name")
    private String exitName;

    @JsonProperty("VAT Rate")
    private String vatRate;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("Chargedamount (net)")
    private String chargedAmountNet;

    @JsonProperty("Chargedamount (gross)")
    private String chargedAmountGross;

    @JsonProperty("Bill RunID2")
    private String billRunId2;
}
