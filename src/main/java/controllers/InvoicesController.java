//package controllers;
//
//import utils.CsvUtils;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//public class InvoicesController {
//    public static void processAllInvoices(String folderName) {
//
//    }
//
//    private static List<DsrcInvCsv> invoicesFromCsv = new ArrayList<>();
//
//    public static void processAllInvoices(String folderName) {
//        try (Stream<Path> paths = Files.walk(Paths.get(folderName))) {
//            paths
//                    .filter(Files::isRegularFile)
//                    .forEach(InvoicesController::processInvoice);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void processInvoice(Path filename) {
//        try {
//            invoicesFromCsv = (List<DsrcInvCsv>) (Object) CsvUtils.readCsv(filename, DsrcInvCsv.class);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        CsvUtils.replaceField("salesPartnerId", "salesPartnerId", invoicesFromCsv, invoicesFromCsv);
//        CsvUtils.replaceFieldOrDeleteLine("aaxCustomerId", "aaxCustomerId", invoicesFromCsv, invoicesFromCsv);
//
//        try {
//            CsvUtils.createCsv("src/main/resources/billrun/processed/"+filename.getFileName().toString(), invoicesFromCsv);
//        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
//            e.printStackTrace();
//        }
//
//        // TODO: generate JSON
//    }
//
//
//    public List<DsrcInvCsv> getInvoicesFromCsv() {
//        return invoicesFromCsv;
//    }
//}
