package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import services.impl.DsrcInvProcessor;
import services.impl.PaymentRequestsProcessor;
import services.impl.TripDetailsProcessor;

//@RequestMapping("/generate")
public class AppController {
    private final DsrcInvProcessor dsrcInvProcessor = new DsrcInvProcessor();
    private final PaymentRequestsProcessor paymentRequestsProcessor = new PaymentRequestsProcessor();
    private final TripDetailsProcessor tripDetailsProcessor = new TripDetailsProcessor();


    // TODO: put that in properties
    private static final String BASE_DIR = "src/main/resources/billrun/TO_DO/";
    private static final String MAPPING_PATH = BASE_DIR + "ec1-epr_customer_mapping.csv";
    private static final String PROCESSED_DIR = "src/main/resources/billrun/PROCESSED/";

    public void processAllInvoices() {
        dsrcInvProcessor.processCsv(BASE_DIR + "BE", MAPPING_PATH, PROCESSED_DIR + "BE/");
    }

    public void processTripDetails() {
        paymentRequestsProcessor.processCsv(BASE_DIR + "PAYMENT_REQUESTS", MAPPING_PATH, PROCESSED_DIR + "PAYMENT_REQUESTS/");
    }

    public void processPaymentRequests() {
        tripDetailsProcessor.processCsv(BASE_DIR + "TRIP_DETAILS", MAPPING_PATH, PROCESSED_DIR + "TRIP_DETAILS/");
    }
}
