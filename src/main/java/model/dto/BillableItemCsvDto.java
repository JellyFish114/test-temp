package sftp.model.dto;

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
@JsonPropertyOrder({"billdocAaxId", "billPeriodEnd", "customerId", "countryCode", "amount", "billableItemId", "amountPerCustomerRegion", "salesPartnerAaxId",
        "customerAaxId", "orderableProductName", "baseProductName", "billDocumentnetAmount"})
public class BillableItemCsvDto {

    @JsonProperty("Billdoc aax id")
    private String billdocAaxId;

    @JsonProperty("Bill period end")
    private String billPeriodEnd;

    @JsonProperty("Customer id")
    private String customerId;

    @JsonProperty("Country code")
    private String countryCode;

    @JsonProperty("Amount")
    private String amount;

    @JsonProperty("Billable item id")
    private String billableItemId;

    @JsonProperty("Amount perCustomer / region")
    private Double amountPerCustomerRegion;

    @JsonProperty("Sales partner aax id")
    private String salesPartnerAaxId;

    @JsonProperty("Customer aax id")
    private String customerAaxId;

    @JsonProperty("Orderable product name")
    private String orderableProductName;

    @JsonProperty("Base product name")
    private String baseProductName;

    @JsonProperty("Bill Documentnet amount")
    private Double billDocumentnetAmount;

    @JsonIgnore
    private String csvSliceName;
}
