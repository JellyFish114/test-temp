package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import services.impl.DsrcInvProcessor;

//@RequestMapping("/generate")
public class AppController {
    private DsrcInvProcessor dsrcInvProcessor = new DsrcInvProcessor();
    private static final String BASE_DIR = "src/main/resources/billrun/TO_DO/";
    private static final String MAPPING_PATH = BASE_DIR + "ec1-epr_customer_mapping.csv";

    public void processAllInvoices() {
        dsrcInvProcessor.processCsv(BASE_DIR + "BE", MAPPING_PATH);
    }

    public void processTripDetails() {
        System.out.println("ProcessTripDetails : work in progress ...");
    }

    public void processPaymentRequests() {
        System.out.println("ProcessPaymentRequests : work in progress ...");
    }
}
