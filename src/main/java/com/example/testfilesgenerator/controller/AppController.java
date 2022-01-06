package com.example.testfilesgenerator.controller;

import com.example.testfilesgenerator.services.ICsvProcessor;
import com.example.testfilesgenerator.services.impl.DsrcInvProcessor;
import com.example.testfilesgenerator.services.impl.PaymentRequestsProcessor;
import com.example.testfilesgenerator.services.impl.TripDetailsProcessor;
import com.example.testfilesgenerator.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class AppController {
    private final ICsvProcessor
            paymentRequestsProcessor,
            tripDetailsProcessor, dsrcInvProcessor;

    @Value("${todo_dir}")
    private String TODO_DIR;
    @Value("${mapping_file_path}")
    private String MAPPING_PATH;
    @Value("${processed_dir}")
    private String PROCESSED_DIR;
    @Value("${allowed_directories}")
    private String ALLOWED_DIR;

    @Autowired
    public AppController(DsrcInvProcessor dsrcInvProcessor, PaymentRequestsProcessor paymentRequestsProcessor, TripDetailsProcessor tripDetailsProcessor) {
        this.dsrcInvProcessor = dsrcInvProcessor;
        this.paymentRequestsProcessor = paymentRequestsProcessor;
        this.tripDetailsProcessor = tripDetailsProcessor;
    }

    @Bean
    public <T> void processCsvs() {

        Path processedDir = Paths.get(PROCESSED_DIR);

        try (Stream<Path> paths = Files.walk(processedDir)) {
            List<Path> pathsToDelete = paths.sorted(Comparator.reverseOrder()).collect(Collectors.toList());

            for (Path path : pathsToDelete) {
                if (!path.getFileName().toString().equals(processedDir.getFileName().toString())) {
                    Files.deleteIfExists(path);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<File> foldersToProcess = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(TODO_DIR), 1)) {
            foldersToProcess = paths
                    .filter(Files::isDirectory)
                    .map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (File file : foldersToProcess) {

            String folderName = file.getName();

            if (folderName.equals("PAYMENT_REQUESTS"))
                paymentRequestsProcessor.processCsv(TODO_DIR + "PAYMENT_REQUESTS", MAPPING_PATH, PROCESSED_DIR + "PAYMENT_REQUESTS/");

            if (folderName.equals("TRIP_DETAILS"))
                tripDetailsProcessor.processCsv(TODO_DIR + "TRIP_DETAILS", MAPPING_PATH, PROCESSED_DIR + "TRIP_DETAILS/");

            if (ALLOWED_DIR.contains(folderName))
                dsrcInvProcessor.processCsv(TODO_DIR + folderName, MAPPING_PATH, PROCESSED_DIR + folderName + "/");

        }

    }

}
